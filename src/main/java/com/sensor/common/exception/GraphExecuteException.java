package com.sensor.common.exception;


/**
 * @author apple
 */
public class GraphExecuteException extends RuntimeException {
    public GraphExecuteException() {
    }

    public GraphExecuteException(String message) {
        super(message);
    }

    public GraphExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphExecuteException(Throwable cause) {
        super(cause);
    }

    public GraphExecuteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
