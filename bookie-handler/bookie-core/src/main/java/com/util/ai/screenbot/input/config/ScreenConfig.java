package com.util.ai.screenbot.input.config;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.util.ai.screenbot.input.constants.SupportedScreenResolution;

/**
 * @author mcop
 * 
 *         Capturing screen dimensions of running device. <b>Works only in
 *         single monitor environment!!!</b>
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
		screenCoef = 1.0;
		// For multi-monitor environemnts
		////////////////////
		// GraphicsDevice gd =
		// GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		// int width = gd.getDisplayMode().getWidth();
		// int height = gd.getDisplayMode().getHeight()
		////////////////////
	}

	public static SupportedScreenResolution getScreenResolution() {

		if (width.equals(1366.0) && height.equals(768.0)) {
			return SupportedScreenResolution.RESOLUTION_1366x768;
		} else {
			throw new IllegalStateException(
					String.format("Not supported resolution! Width: %s Height: %s", width, height));
		}
	}
}
