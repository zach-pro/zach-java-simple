package com.sensor.common.exception;


/**
 * @author apple
 * Nebula初始化异常
 */
public class NebulaInitException extends RuntimeException {
    public NebulaInitException() {
    }

    public NebulaInitException(String message) {
        super(message);
    }

    public NebulaInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public NebulaInitException(Throwable cause) {
        super(cause);
    }

    public NebulaInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
