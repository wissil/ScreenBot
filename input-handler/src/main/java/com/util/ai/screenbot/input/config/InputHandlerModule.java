package com.util.ai.screenbot.input.config;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.WinScreenHandler;

public class InputHandlerModule extends AbstractModule {
	
	@Provides
	@Singleton
	ScriptEngine scriptEngine() {
		return new ScriptEngineManager().getEngineByName("AppleScriptEngine");
	}

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
        return new WinScreenHandler();
    }
}
