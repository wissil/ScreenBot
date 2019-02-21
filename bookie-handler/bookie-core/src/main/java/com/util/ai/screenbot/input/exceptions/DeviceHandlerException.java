package com.util.ai.screenbot.input.exceptions;

public class DeviceHandlerException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DeviceHandlerException(String message) {
        super(message);
    }

    public DeviceHandlerException(String message, Throwable t) {
        super(message, t);
    }
}
