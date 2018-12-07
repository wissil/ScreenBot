package com.util.ai.screenbot.input.utils;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.util.ai.screenbot.input.exceptions.DeviceHandlerException;

/**
 * @author mcop
 * 
 *         Used detecting elements on the screen
 */
public abstract class ScreenHandler extends AbstractDeviceHandler {

    public Color detectColor(Integer x, Integer y) {
        return robot.getPixelColor(x, y);
    }

    public void takeScreenshot(Integer x, Integer y, Integer width, Integer height, String screenShotName, String format, String path) {

        Rectangle screenshotFrame = new Rectangle(x, y, width, height);
        BufferedImage screenshot = robot.createScreenCapture(screenshotFrame);
        File image = new File(path, screenShotName + "." + format);

        try {
            ImageIO.write(screenshot, format, image);
        } catch (IOException e) {
            throw new DeviceHandlerException("Can not take screenshot", e);
        }
    }

    public abstract Rectangle getRect();

    public abstract String getCurrentWindowName();

}
