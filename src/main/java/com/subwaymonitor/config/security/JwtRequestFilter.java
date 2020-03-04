package com.subwaymonitor.config.security;

import com.subwaymonitor.services.impl.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  private final Logger logger;

  @Autowired
  public JwtRequestFilter(UserService userService, JwtTokenUtil jwtTokenUtil) {
    this.userService = userService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.logger = Logger.getLogger(this.getClass());
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String authorization = request.getHeader("Authorization");

    String username = null;
    String token = null;

    if (authorization != null && authorization.startsWith("Bearer ")) {
      token = authorization.substring(7);

      try {
        username = this.jwtTokenUtil.getUsernameFromToken(token);
      } catch (IllegalArgumentException e) {
        this.logger.error("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        this.logger.error("JWT Token has expired");
      }
    } else {
      this.logger.warn("JWT Token does not begin with Bearer String");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userService.loadUserByUsername(username);

      if (this.jwtTokenUtil.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        ;

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}
