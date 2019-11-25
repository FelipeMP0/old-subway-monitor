package com.subwaymonitor.presenters;

import com.subwaymonitor.models.LineStatus;

import java.time.ZonedDateTime;

public class LineStatusPresenter {

    private LinePresenter line;

    private StatusPresenter status;

    private Integer verificationNumber;

    private ZonedDateTime dateTime;

    public LineStatusPresenter(LineStatus lineStatus) {
        if (lineStatus != null) {
            if (lineStatus.getLine() != null) {
                this.line = new LinePresenter(lineStatus.getLine());
            }

            if (lineStatus.getStatus() != null) {
                this.status = new StatusPresenter(lineStatus.getStatus());
            }

            this.verificationNumber = lineStatus.getVerificationNumber();
            this.dateTime = lineStatus.getCreationDate();
        }
    }

    public LinePresenter getLine() {
        return line;
    }

    public StatusPresenter getStatus() {
        return status;
    }

    public Integer getVerificationNumber() {
        return verificationNumber;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

}
