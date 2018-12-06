package com.util.ai.screenbot.input.utils;

import java.awt.AWTException;
import java.awt.Robot;

import com.util.ai.screenbot.input.exceptions.DeviceHandlerException;

public abstract class AbstractDeviceHandler {

    protected Robot robot;

    public AbstractDeviceHandler() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new DeviceHandlerException("Can not create device handler");
        }
    }

}
