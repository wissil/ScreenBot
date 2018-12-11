package com.util.ai.screenbot.input.config;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.util.ai.screenbot.input.constants.ScreenResolution;

/**
 * @author mcop
 * 
 *         Capturing screen dimensions of running device. <b>Works only in single monitor environment!!!</b>
 */
public class ScreenConfig {

    public static Double width;
    public static Double height;
    public static Integer resolution; // Dots per inch
    public static Double screenCoef;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        resolution = Toolkit.getDefaultToolkit().getScreenResolution();

        // screenCoef = width / height / (16.0 / 9.0);
        screenCoef = 1.15;
        // For multi-monitor environemnts
        ////////////////////
        // GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // int width = gd.getDisplayMode().getWidth();
        // int height = gd.getDisplayMode().getHeight()
        ////////////////////
    }

    public static ScreenResolution getScreenResolution() {
        // FIXME
        if (resolution == 96) {
            return ScreenResolution.RESOLUTION_96;
        } else {
            return null;
        }
    }
}
