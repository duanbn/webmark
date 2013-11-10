package com.dy.webmark.exception;

import com.dy.webmark.common.ErrorCode;


public class BizException extends Exception {

    private ErrorCode ec;

    public BizException(ErrorCode ec) {
        super(ec.getCode() + ":" + ec.getMessage());
        this.ec = ec;
    }

    public BizException(ErrorCode ec, Exception e) {
        super(ec.getCode() + ":" + ec.getMessage(), e);
        this.ec = ec;
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException(String msg, Exception e) {
        super(msg, e);
    }

    public ErrorCode getEc() {
        return ec;
    }

    public void setEc(ErrorCode ec) {
        this.ec = ec;
    }

}
