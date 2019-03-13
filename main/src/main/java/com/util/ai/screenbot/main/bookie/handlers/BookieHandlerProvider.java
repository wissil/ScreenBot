package com.util.ai.screenbot.main.bookie.handlers;

import com.google.inject.Inject;
import com.util.ai.screenbot.main.bookie.handlers.specific.Bet365Handler;
import com.util.ai.screenbot.main.bookie.handlers.specific.ExpertHandler;
import com.util.ai.screenbot.main.bookie.handlers.specific.MarathonBetHandler;
import com.util.ai.screenbot.main.bookie.handlers.specific.WilliamHillHandler;

public class BookieHandlerProvider {

    @Inject
    private static MarathonBetHandler marathonBetHandler;

    @Inject
    private static WilliamHillHandler williamHillHandler;

    @Inject
    private static Bet365Handler bet365Handler;

    @Inject
    private static ExpertHandler expertHandler;

    public static MarathonBetHandler marathonBetHandler() {
        return marathonBetHandler;
    }

    public static WilliamHillHandler williamHillHandler() {
        return williamHillHandler;
    }

    public static Bet365Handler bet365Handler() {
        return bet365Handler;
    }

    public static ExpertHandler expertHandler() {
        return expertHandler;
    }
}
