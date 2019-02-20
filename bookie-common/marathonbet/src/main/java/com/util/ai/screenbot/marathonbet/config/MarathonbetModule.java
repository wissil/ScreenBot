package com.util.ai.screenbot.marathonbet.config;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.SupportedScreenResolution;
import com.util.ai.screenbot.input.constants.marathonbet.AbstractMarathonbetConstants;
import com.util.ai.screenbot.input.constants.marathonbet.DefaultMarathonbetConstants;
import com.util.ai.screenbot.input.constants.marathonbet.MarathonbetConstants_1366x768;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.marathonbet.MarathonbetInputBot;
import com.util.ai.screenbot.support.platform.Platform;
import com.util.ai.screenbot.support.platform.PlatformResolver;

public class MarathonbetModule extends AbstractModule {
	
	private static final Platform PLATFORM = PlatformResolver.resolveCurrentPlatform();
	
	@Provides
	@Singleton
	AbstractMarathonbetConstants marathonbetConstants() {
		if (PLATFORM.equals(Platform.MAC))
			return new DefaultMarathonbetConstants();

		final SupportedScreenResolution resolution = ScreenConfig.getScreenResolution();

		switch (resolution) {
		case RESOLUTION_1366x768:
			return new MarathonbetConstants_1366x768();
		default:
			throw new IllegalArgumentException(String.format("Not supported resolution %s", resolution));
		}

	}

	@Inject
	@Provides
	@Singleton
	MarathonbetInputBot marathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler, AbstractMarathonbetConstants marathonbetConstants) {
		return new MarathonbetInputBot(keyboardHandler, screenHandler, mouseHandler, marathonbetConstants);
	}
}
