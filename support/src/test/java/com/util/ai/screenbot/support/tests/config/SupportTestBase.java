package com.util.ai.screenbot.support.tests.config;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.support.config.SupportModule;

public class SupportTestBase {

	private final Injector injector = 
			Guice.createInjector(new SupportModule());
	
	@Before
	public void prepareGuice() {
		injector.injectMembers(this);
	}
}
