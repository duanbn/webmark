package com.dy.webmark.exception;


public class FavoriteException extends Exception {

    public FavoriteException(ErrorCode sc) {
        super(sc.getCode() + ":" + sc.getValue());
    }

    public FavoriteException(ErrorCode sc, Exception e) {
        super(sc.getCode() + ":" + sc.getValue(), e);
    }

    public FavoriteException(String msg) {
        super(msg);
    }

    public FavoriteException(String msg, Exception e) {
        super(msg, e);
    }

}
