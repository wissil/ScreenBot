package com.util.ai.screenbot.input.logic;

import java.util.Objects;

import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public abstract class AbstractInputBot {

	protected final ScreenHandler screenHandler;

	protected final KeyboardHandler keyboardHandler;

	protected final MouseHandler mouseHandler;
	
	protected AbstractInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler) {
		this.screenHandler = Objects.requireNonNull(screenHandler);
		this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
		this.mouseHandler = Objects.requireNonNull(mouseHandler);
	}

	public void write(String text) {
		this.keyboardHandler.write(text);
	}

	protected class BotCoordinates {
		public Integer x;
		public Integer y;

		public BotCoordinates(Integer x, Integer y) {
			this.x = x;
			this.y = y;
		}

	}

}
