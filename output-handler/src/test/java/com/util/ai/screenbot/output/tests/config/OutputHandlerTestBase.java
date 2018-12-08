package com.util.ai.screenbot.output.tests.config;

import org.junit.Before;
import org.junit.Rule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.output.config.OutputHandlerModule;
import com.util.ai.screenbot.support.testing.time.JUnitStopwatch;

public class OutputHandlerTestBase {
	
	private final Injector injector = 
			Guice.createInjector(new OutputHandlerModule());
	
	@Rule
	public JUnitStopwatch stopwatch = new JUnitStopwatch();

	@Before
	public void prepareGuice() {
		injector.injectMembers(this);
	}
}
