package com.sensor.common.exception;


/**
 * @author apple
 * 自定义Quartz异常
 */
public class QuartzCustomException extends RuntimeException {
    public QuartzCustomException() {
    }

    public QuartzCustomException(String message) {
        super(message);
    }

    public QuartzCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuartzCustomException(Throwable cause) {
        super(cause);
    }

    public QuartzCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
