package com.util.ai.screenbot.bookie.core.test.config;

import org.junit.Before;
import org.junit.Rule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.bookie.core.config.BookieCoreModule;
import com.util.ai.screenbot.ocr.config.OcrHandlerModule;
import com.util.ai.screenbot.support.config.SupportModule;
import com.util.ai.screenbot.support.testing.time.JUnitStopwatch;

public class BookieCoreTestBase {
	
	private final Injector injector = 
			Guice.createInjector(new BookieCoreModule(), new SupportModule(), new OcrHandlerModule());
	
	@Rule
	public JUnitStopwatch stopwatch = new JUnitStopwatch();

	@Before
	public void prepareGuice() {
		injector.injectMembers(this);
	}
}
