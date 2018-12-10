package com.util.ai.screenbot.input.config;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.MacScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.WinScreenHandler;
import com.util.ai.screenbot.input.logic.ValueBettingBot;
import com.util.ai.screenbot.support.platform.Platform;
import com.util.ai.screenbot.support.platform.PlatformResolver;

public class InputHandlerModule extends AbstractModule {

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
    ScriptEngine scriptEngine() {
        return new ScriptEngineManager().getEngineByName("AppleScriptEngine");
    }

    @Inject
    @Provides
    @Singleton
    ScreenHandler screenHandler(ScriptEngine engine) {
        final Platform platform = PlatformResolver.resolveCurrentPlatform();

        switch (platform) {
        case WINDOWS:
            return new WinScreenHandler();
        case MAC:
            return new MacScreenHandler(engine);
        default:
            throw new IllegalArgumentException(String.format("Platform %s is not known.", platform));
        }
    }

    @Inject
    @Provides
    @Singleton
    ValueBettingBot valueBettingBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler) {
        return new ValueBettingBot(keyboardHandler, screenHandler);
    }
}
