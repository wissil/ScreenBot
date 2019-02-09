package com.util.ai.screenbot.input.logic.value.betting;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.value.betting.AbstractVBConstants;
import com.util.ai.screenbot.input.exceptions.ValueBettingAppException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;

public abstract class VBInputBot extends AbstractInputBot {

	protected static final Logger log = LoggerFactory.getLogger(VBInputBot.class);

	protected final Integer MAX_NUMBER_OF_HOPS = 10;

	protected AbstractVBConstants vbConstants;

	protected VBInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractVBConstants vbConstants) {
		super(keyboardHandler, screenHandler, mouseHandler);
		this.vbConstants = Objects.requireNonNull(vbConstants);
	}

	public Boolean isWindowInForeground(String windowName) {
		String currentWindowName = screenHandler.getActiveWindow().getName();
		log.info("Current app: " + currentWindowName);
		return currentWindowName.trim().toLowerCase().startsWith(windowName.toLowerCase());

	}

	protected Rectangle checkScreen() {
		Rectangle screen = screenHandler.getActiveWindow().getBounds();

		log.debug("VB screen width: " + screen.width);
		log.debug("VB screen height: " + screen.height);

		// App works only if Value Betting window is full screened
		checkIsWindowFullScreen(screen);

		return screen;
	}

	protected void checkIsWindowFullScreen(Rectangle screen) {

		// If full screen Value Betting app screen width should not be lower than
		// monitor screen width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		log.debug("Screen width:" + screenSize.getWidth());
		if (screen.getWidth() < screenSize.getWidth()) {
			throw new ValueBettingAppException("Value Betting app should be started in full screen");
		}
	}

}
