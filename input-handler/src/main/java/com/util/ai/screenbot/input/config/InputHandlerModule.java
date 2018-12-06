package com.util.ai.screenbot.input.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.utils.KeyboardHandler;
import com.util.ai.screenbot.input.utils.MouseHandler;
import com.util.ai.screenbot.input.utils.ScreenHandler;
import com.util.ai.screenbot.input.utils.WinScreenHandler;

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
        return new WinScreenHandler();
    }
}
