package com.util.ai.screenbot.input.handlers.screen;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.util.ai.screenbot.input.exceptions.DeviceHandlerException;
import com.util.ai.screenbot.input.handlers.AbstractDeviceHandler;
import com.util.ai.screenbot.input.model.ActiveWindow;

/**
 * @author mcop
 * 
 *         Used detecting elements on the screen
 */
public abstract class ScreenHandler extends AbstractDeviceHandler {

    public Color detectColor(Integer x, Integer y) {
        return robot.getPixelColor(x, y);
    }

    public BufferedImage takeScreenshot(Integer x, Integer y, Integer width, Integer height) {

        Rectangle screenshotFrame = new Rectangle(x, y, width, height);
        return robot.createScreenCapture(screenshotFrame);

    }

    public void takeScreenshot(Integer x, Integer y, Integer width, Integer height, String screenShotName, String format, String path) {

        BufferedImage screenshot = takeScreenshot(x, y, width, height);
        File image = new File(path, screenShotName + "." + format);

        try {
            ImageIO.write(screenshot, format, image);
        } catch (IOException e) {
            throw new DeviceHandlerException("Can not take screenshot", e);
        }
    }

    public abstract ActiveWindow getActiveWindow();

}
