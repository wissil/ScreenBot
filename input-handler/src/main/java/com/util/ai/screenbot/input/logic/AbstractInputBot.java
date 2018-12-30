package com.util.ai.screenbot.input.logic;

import java.util.Objects;

import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public abstract class AbstractInputBot {

	protected ScreenHandler screenHandler;

	protected KeyboardHandler keyboardHandler;

	protected MouseHandler mouseHandler;

	protected AbstractInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler) {
		this.screenHandler = Objects.requireNonNull(screenHandler);
		this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
		this.mouseHandler = Objects.requireNonNull(mouseHandler);
	}

	public void write(String text) {
		this.keyboardHandler.write(text);
	}

	protected class BetCoordinates {
		public Integer x;
		public Integer y;

		public BetCoordinates(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}

	}

}
