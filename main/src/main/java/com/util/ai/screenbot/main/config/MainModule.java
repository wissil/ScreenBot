package com.util.ai.screenbot.main.config;


import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.main.automata.VBStateMachine;
import com.util.ai.screenbot.main.automata.VBStateMachineMock;
import com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider;
import com.util.ai.screenbot.main.bookie.handlers.specific.Bet365Handler;
import com.util.ai.screenbot.main.bookie.handlers.specific.MarathonBetHandler;
import com.util.ai.screenbot.main.bookie.handlers.specific.WilliamHillHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandlerImpl;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandlerImpl;

public class MainModule extends AbstractModule {

    @Override
    protected void configure() {
        requestStaticInjection(BookieHandlerProvider.class);
    }

    @Inject
    @Provides
    @Singleton
    MarathonBetHandler marathonHandler(InputHandler in) {
        return new MarathonBetHandler(in);
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
    VBStateMachine stateMachine(InputHandler in, OutputHandler out) {
        return new VBStateMachineMock(in, out);
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
