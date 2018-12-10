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

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        resolution = Toolkit.getDefaultToolkit().getScreenResolution();

        // For multi-monitor environemnts
        ////////////////////
        // GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // int width = gd.getDisplayMode().getWidth();
        // int height = gd.getDisplayMode().getHeight()
        ////////////////////
    }

    public static ScreenResolution getScreenResolution() {
        return null; // FIXME
    }
}
