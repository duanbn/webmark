package com.dy.webmark.exception;


public class FavoriteException extends Exception {
    
    private ErrorCode ec;

    public FavoriteException(ErrorCode ec) {
        super(ec.getCode() + ":" + ec.getMessage());
        this.ec = ec;
    }

    public FavoriteException(ErrorCode ec, Exception e) {
        super(ec.getCode() + ":" + ec.getMessage(), e);
        this.ec = ec;
    }

    public FavoriteException(String msg) {
        super(msg);
    }

    public FavoriteException(String msg, Exception e) {
        super(msg, e);
    }

    public ErrorCode getEc() {
        return ec;
    }

    public void setEc(ErrorCode ec) {
        this.ec = ec;
    }

}
