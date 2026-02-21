package com.globalcontenthub.userprofile.exceptions;

public class UserProfileException extends Exception {
    private String errorCode;
    private int httpStatusCode;

    public UserProfileException(String message) {
        super(message);
        this.errorCode = "PROFILE_ERROR";
        this.httpStatusCode = 500;
    }

    public UserProfileException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatusCode = 500;
    }

    public UserProfileException(String message, String errorCode, int httpStatusCode) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    public UserProfileException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "PROFILE_ERROR";
        this.httpStatusCode = 500;
    }

    public String getErrorCode() { return errorCode; }
    public int getHttpStatusCode() { return httpStatusCode; }
}

