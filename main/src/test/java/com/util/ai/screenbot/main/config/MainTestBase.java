package com.util.ai.screenbot.main.config;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.input.config.InputHandlerModule;
import com.util.ai.screenbot.output.config.OutputHandlerModule;
import com.util.ai.screenbot.support.config.SupportModule;

public class MainTestBase {
	
	private final Injector injector = 
			Guice.createInjector(
					new MainModule(), 
					new SupportModule(), 
					new OutputHandlerModule(),
					new InputHandlerModule());

	@Before
	public void prepareGuice() {
		injector.injectMembers(this);
	}
}
