package com.util.ai.screenbot.marathonbet.config;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.marathonbet.MarathonbetInputBot;

public class MarathonbetModule extends AbstractModule {
	
	@Inject
	@Provides
	@Singleton
	MarathonbetInputBot marathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler) {
		return new MarathonbetInputBot(keyboardHandler, screenHandler, mouseHandler);
	}
}
