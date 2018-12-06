package com.util.ai.screenbot.output.tests.config;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.output.config.OutputHandlerModule;

public class OutputHandlerTestBase {
	
	private final Injector injector = 
			Guice.createInjector(new OutputHandlerModule());

	@Before
	public void prepareGuice() {
		injector.injectMembers(this);
	}
}
