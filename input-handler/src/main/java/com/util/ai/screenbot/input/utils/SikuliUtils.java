package com.util.ai.screenbot.input.utils;

import java.awt.image.BufferedImage;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Screen;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.logic.AbstractInputBot;

public class SikuliUtils {

	private SikuliUtils() {
	}

	static {
		// initialize
		ImagePath.add(AbstractInputBot.class.getCanonicalName() + "/images");
	}

	/** In seconds. */
	private static final int DEFAULT_WAIT_TIMEOUT = 5;

	/** Hold after click, in millis. */
	private static final int HOLD_AFTER_CLICK = 200;

	/** Hold before click, in millis. */
	private static final int HOLD_BEFORE_CLICK = 200;

	private static final Screen SCREEN = new Screen();

	public static void clickOnElement(String elementPath) throws FatalVBException {
		clickOnElement(elementPath, DEFAULT_WAIT_TIMEOUT);
	}

	public static void clickOnElement(String elementPath, int timeoutMs) throws FatalVBException {
		try {
			SCREEN.wait(elementPath, timeoutMs);
			Thread.sleep(HOLD_BEFORE_CLICK);

			SCREEN.click(elementPath);
			Thread.sleep(HOLD_AFTER_CLICK);
		} catch (FindFailed | InterruptedException e) {
			throw new FatalVBException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static void waitForElement(String elementPath, int timeoutMs) throws FatalVBException {
		try {
			SCREEN.wait(elementPath, timeoutMs);
		} catch (FindFailed e) {
			throw new FatalVBException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static boolean writeToElement(String elementPath, String text) throws FatalVBException {
		try {
			SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT);
			Thread.sleep(HOLD_BEFORE_CLICK);

			SCREEN.click(elementPath);
			SCREEN.write(text);
			Thread.sleep(HOLD_AFTER_CLICK);

			return true;
		} catch (FindFailed | InterruptedException e) {
			return false;
		}
	}

	public static BufferedImage getImageRigtToElement(String elementPath, int pixels) throws FatalVBException {
		try {
			Thread.sleep(HOLD_BEFORE_CLICK);
			return SCREEN.capture(SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT).right(pixels)).getImage();
		} catch (FindFailed | InterruptedException e) {
			throw new FatalVBException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static BufferedImage getImageBelowElement(String elementPath, int pixels) throws FatalVBException {
		try {
			Thread.sleep(HOLD_BEFORE_CLICK);
			return SCREEN.capture(SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT).below(pixels)).getImage();
		} catch (FindFailed | InterruptedException e) {
			throw new FatalVBException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}
}
