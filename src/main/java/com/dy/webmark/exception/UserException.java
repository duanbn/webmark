package com.dy.webmark.exception;


public class UserException extends Exception {

    private ErrorCode ec;

    public UserException(ErrorCode ec) {
        super(ec.getCode() + ":" + ec.getMessage());
        this.ec = ec;
    }

    public UserException(ErrorCode ec, Exception e) {
        super(ec.getCode() + ":" + ec.getMessage(), e);
        this.ec = ec;
    }

    public UserException(String msg) {
        super(msg);
    }

    public UserException(String msg, Exception e) {
        super(msg, e);
    }

    public ErrorCode getEc() {
        return ec;
    }

    public void setEc(ErrorCode ec) {
        this.ec = ec;
    }

}
