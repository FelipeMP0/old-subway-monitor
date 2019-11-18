package com.subwaymonitor.presenters;

public class ErrorPresenter {

    private String field;
    private String message;

    public ErrorPresenter(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
