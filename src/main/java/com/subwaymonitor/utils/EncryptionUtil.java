package com.subwaymonitor.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtil {

  public static String BCryptEncode(String string) {
    return new BCryptPasswordEncoder().encode(string);
  }
}
