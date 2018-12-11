package com.util.ai.screenbot.input.config;

import javax.script.ScriptEngineManager;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.constants.AbstractVBConstants;
import com.util.ai.screenbot.input.constants.SupportedScreenResolution;
import com.util.ai.screenbot.input.constants.VBConstants_1600x900;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.MacScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.handlers.screen.WinScreenHandler;
import com.util.ai.screenbot.input.logic.VBInputBot;
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
    ScreenHandler screenHandler() {
        final Platform platform = PlatformResolver.resolveCurrentPlatform();

        switch (platform) {
        case WINDOWS:
            return new WinScreenHandler();
        case MAC:
            return new MacScreenHandler(new ScriptEngineManager().getEngineByName("AppleScriptEngine"));
        default:
            throw new IllegalArgumentException(String.format("Platform %s is not known.", platform));
        }
    }

    @Provides
    @Singleton
    AbstractVBConstants vbConstants() {

        final SupportedScreenResolution resolution = ScreenConfig.getScreenResolution();

        switch (resolution) {
        case RESOLUTION_1600x900:
            return new VBConstants_1600x900();
        default:
            throw new IllegalArgumentException(String.format("Not supported resolution %s", resolution));
        }

    }

    @Inject
    @Provides
    @Singleton
    VBInputBot valueBettingBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
        return new VBInputBot(keyboardHandler, screenHandler, mouseHandler, vbConstants);
    }

}
