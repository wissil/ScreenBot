package com.util.ai.screenbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.util.ai.screenbot.input.logic.ValueBettingBot;

public class RebelService {

    @Inject
    private ValueBettingBot valueBettingBot;

    private static final Logger log = LoggerFactory.getLogger(RebelService.class);

    public void run() throws InterruptedException {
        log.info("Service started successfully!");

        valueBettingBot.switchToValueBetting();

        while (true) {
            Thread.sleep(1000);
            log.debug("Running ...");
        }
    }
}
