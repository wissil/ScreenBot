package com.util.ai.screenbot.main.bookie;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.util.ai.screenbot.main.bookie.handlers.specific.Bet365Handler;
import com.util.ai.screenbot.main.bookie.handlers.specific.MarathonBetHandler;
import com.util.ai.screenbot.main.bookie.handlers.specific.WilliamHillHandler;
import com.util.ai.screenbot.main.config.MainTestBase;


public class BookieProviderTest extends MainTestBase {
		
    @Test
    public void shouldNotThrow() {
    		assertTrue(Bookie.BET_365.getHandler() instanceof Bet365Handler);
    		assertTrue(Bookie.MARATHON_BET.getHandler() instanceof MarathonBetHandler);
    		assertTrue(Bookie.WILLIAM_HILL.getHandler() instanceof WilliamHillHandler);
    }
}
