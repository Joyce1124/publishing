package com.best.publishing.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final Log logger = LogFactory.getLog(BizException.class);

    public BizException(Exception e) {
        super(e);
    }

    public BizException(String msg) {
        super(msg);
    }

    public BizException() {
    }

    public BizException(Throwable e) {
        super(e);
    }

    public BizException(Throwable e, String msg) {
        super(msg, e);
    }
}
