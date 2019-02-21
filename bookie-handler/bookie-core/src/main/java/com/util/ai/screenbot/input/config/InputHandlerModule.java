package com.util.ai.screenbot.input.config;

import javax.script.ScriptEngineManager;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.constants.SupportedScreenResolution;
import com.util.ai.screenbot.input.constants.value.betting.AbstractVBConstants;
import com.util.ai.screenbot.input.constants.value.betting.DefaultVBConstants;
import com.util.ai.screenbot.input.constants.value.betting.VBConstants_1366x768;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.MacScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.WinScreenHandler;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.support.platform.Platform;
import com.util.ai.screenbot.support.platform.PlatformResolver;

public class InputHandlerModule extends AbstractModule {

	private static final Platform PLATFORM = PlatformResolver.resolveCurrentPlatform();

	@Provides
	@Singleton
	KeyboardHandler keyboardHandler() {
		return new KeyboardHandler();
	}

	@Provides
	@Singleton
	MouseHandler mouseHandler() {
		return new MouseHandler();
	}

	@Provides
	@Singleton
	ScreenHandler screenHandler() {
		switch (PLATFORM) {
		case WINDOWS:
			return new WinScreenHandler();
		case MAC:
			return new MacScreenHandler(new ScriptEngineManager().getEngineByName("AppleScriptEngine"));
		default:
			throw new IllegalArgumentException(String.format("Platform %s is not known.", PLATFORM));
		}
	}

	@Provides
	@Singleton
	AbstractVBConstants vbConstants() {
		if (PLATFORM.equals(Platform.MAC))
			return new DefaultVBConstants();

		final SupportedScreenResolution resolution = ScreenConfig.getScreenResolution();

		switch (resolution) {
		case RESOLUTION_1366x768:
			return new VBConstants_1366x768();
		default:
			throw new IllegalArgumentException(String.format("Not supported resolution %s", resolution));
		}

	}

	@Inject
	@Provides
	@Singleton
	VBMainInputBot valueBettingBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
		return new VBMainInputBot(keyboardHandler, screenHandler, mouseHandler, vbConstants);
	}

	@Inject
	@Provides
	@Singleton
	VBBrowserInputBot valueBettingBrowserBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
		return new VBBrowserInputBot(keyboardHandler, screenHandler, mouseHandler, vbConstants);
	}

}
