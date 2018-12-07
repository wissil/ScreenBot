package com.util.ai.screenbot.input.config;

import org.junit.Before;
import org.junit.Rule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.input.config.InputHandlerModule;
import com.util.ai.screenbot.support.testing.IgnoreOnPlatformRule;

public class InputHandlerTestBase {
    private final Injector injector = Guice.createInjector(new InputHandlerModule());

    @Rule
    public IgnoreOnPlatformRule dataSetRule = new IgnoreOnPlatformRule();
    
    @Before
    public void prepareGuice() {
        injector.injectMembers(this);
    }
}
