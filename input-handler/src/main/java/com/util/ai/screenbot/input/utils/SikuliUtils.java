package com.util.ai.screenbot.input.utils;

import java.awt.image.BufferedImage;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.logic.AbstractInputBot;

public class SikuliUtils {

	private static final Logger log = LoggerFactory.getLogger(SikuliUtils.class);

	private SikuliUtils() {
	}

	static {
		// initialize
		ImagePath.add(AbstractInputBot.class.getCanonicalName() + "/images");
	}

	/** Percentage indicating similarity that is considered as exact. */
	private static final double EXACT_SIMILARITY = 0.97;

	/** In seconds. */
	private static final int DEFAULT_WAIT_TIMEOUT = 5;

	/** Hold after click, in millis. */
	private static final int HOLD_AFTER_CLICK = 50;

	/** Hold before click, in millis. */
	private static final int HOLD_BEFORE_CLICK = 50;

	private static final Screen SCREEN = new Screen();

	public static void clickOnElementFast(String elementPath) throws GuiElementNotFoundException {
		try {
			SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT);
			SCREEN.click(elementPath);
		} catch (FindFailed e) {
			throw new GuiElementNotFoundException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static void clickOnElement(String elementPath) throws GuiElementNotFoundException {
		clickOnElement(elementPath, DEFAULT_WAIT_TIMEOUT);
	}

	public static void clickOnElement(String elementPath, int timeoutMs) throws GuiElementNotFoundException {
		try {
			Thread.sleep(HOLD_BEFORE_CLICK);
			SCREEN.wait(elementPath, timeoutMs);

			SCREEN.click(elementPath);
			Thread.sleep(HOLD_AFTER_CLICK);
		} catch (FindFailed | InterruptedException e) {
			throw new GuiElementNotFoundException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static boolean waitForElement(String elementPath, int timeoutMs) {
		try {
			SCREEN.wait(elementPath, timeoutMs);
			return true;
		} catch (FindFailed e) {
			return false;
		}
	}

	public static boolean writeToElement(String elementPath, String text) {
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

	public static BufferedImage getImageRigtToElement(String elementPath, int pixels)
			throws GuiElementNotFoundException {
		try {
			Thread.sleep(HOLD_BEFORE_CLICK);
			return SCREEN.capture(SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT).right(pixels)).getImage();
		} catch (FindFailed | InterruptedException e) {
			throw new GuiElementNotFoundException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static BufferedImage getImageBelowElement(String elementPath, int pixels)
			throws GuiElementNotFoundException {
		try {
			Thread.sleep(HOLD_BEFORE_CLICK);
			return SCREEN.capture(SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT).below(pixels)).getImage();
		} catch (FindFailed | InterruptedException e) {
			throw new GuiElementNotFoundException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static void clickBelowElement(String elementPath, int pixels) throws GuiElementNotFoundException {
		try {
			Thread.sleep(HOLD_BEFORE_CLICK);
			SCREEN.wait(elementPath, DEFAULT_WAIT_TIMEOUT).below(pixels).click();

			Thread.sleep(HOLD_AFTER_CLICK);
		} catch (FindFailed | InterruptedException e) {
			throw new GuiElementNotFoundException(String.format("Couldn't find element %s.", elementPath), e);
		}
	}

	public static boolean waitForElementToVanish(String elementPath, int timeoutMs) {
		return SCREEN.waitVanish(elementPath, timeoutMs);
	}

	public static boolean waitForTargetToVanishBelowBase(String basePath, String targetPath, int pixels,
			double timeout) {
		try {
			final Region region = SCREEN.wait(basePath).below(pixels);
			region.click();

			log.info("SIM: " + region.find(targetPath).getScore());

			if (region.find(targetPath).getScore() < EXACT_SIMILARITY)
				return true;
			return region.waitVanish(targetPath, timeout);
		} catch (FindFailed e) {
			return false;
		}
	}

}
