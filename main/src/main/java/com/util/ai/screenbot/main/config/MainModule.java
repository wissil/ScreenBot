package com.util.ai.screenbot.main.config;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.logic.marathonbet.MarathonbetInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.main.automata.VBStateMachine;
import com.util.ai.screenbot.main.automata.VBStateMachineImpl;
import com.util.ai.screenbot.main.automata.VBStateMachineMock;
import com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider;
import com.util.ai.screenbot.main.bookie.handlers.specific.Bet365Handler;
import com.util.ai.screenbot.main.bookie.handlers.specific.MarathonBetHandler;
import com.util.ai.screenbot.main.bookie.handlers.specific.WilliamHillHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandlerImpl;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandlerImpl;
import com.util.ai.screenbot.support.email.EmailSender;
import com.util.ai.screenbot.support.platform.Platform;
import com.util.ai.screenbot.support.platform.PlatformResolver;

public class MainModule extends AbstractModule {

	@Override
	protected void configure() {
		requestStaticInjection(BookieHandlerProvider.class);
	}

	@Inject
	@Provides
	@Singleton
	MarathonBetHandler marathonHandler(InputHandler in, MarathonbetInputBot marathonbetInputBot) {
		return new MarathonBetHandler(in, marathonbetInputBot);
	}

	@Inject
	@Provides
	@Singleton
	WilliamHillHandler williamHillBetHandler(InputHandler in) {
		return new WilliamHillHandler(in);
	}

	@Inject
	@Provides
	@Singleton
	Bet365Handler bet365Handler(InputHandler in) {
		return new Bet365Handler(in);
	}

	@Inject
	@Provides
	@Singleton
	VBStateMachine stateMachine(InputHandler in, OutputHandler out, EmailSender emailSender) {
		if (PlatformResolver.resolveCurrentPlatform().equals(Platform.MAC)) {
			// use mock state machine for Mac, as program can't be installed there
			return new VBStateMachineMock(in, out);
		}
		
		if (PlatformResolver.resolveCurrentPlatform().equals(Platform.WINDOWS)) {
			return new VBStateMachineImpl(in, out, emailSender);
		}
		
		return null;
	}

	@Inject
	@Provides
	@Singleton
	InputHandler inputHandler(VBMainInputBot mainBot, VBBrowserInputBot browserBot) {
		return new InputHandlerImpl(mainBot, browserBot);
	}

	@Provides
	@Singleton
	OutputHandler outputHandler() {
		return new OutputHandlerImpl();
	}
}
