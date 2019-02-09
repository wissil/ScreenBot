package com.util.ai.screenbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.input.config.InputHandlerModule;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.config.MainModule;
import com.util.ai.screenbot.output.config.OutputHandlerModule;
import com.util.ai.screenbot.support.config.SupportModule;

public class App {

	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws InterruptedException, FatalValueBettingException {
		log.info("Creating Guice injector ...");
		final Injector injector = Guice.createInjector(new MainModule(), new SupportModule(), new InputHandlerModule(),
				new OutputHandlerModule());

		final RebelService rebel = injector.getInstance(RebelService.class);

		log.info("Starting the service ...");
		rebel.run();
	}
}
