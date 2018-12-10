package com.util.ai.screenbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.util.ai.screenbot.input.logic.VBInputBot;

public class RebelService {

    @Inject
    private VBInputBot valueBettingBot;

    private static final Logger log = LoggerFactory.getLogger(RebelService.class);

    public void run() throws InterruptedException {
        log.info("Service started successfully!");

        valueBettingBot.initialize();

        valueBettingBot.navigateToTopBetUpperLeft();

        Thread.sleep(3000); // Wait for 3s for user to check

        valueBettingBot.navigateToTopBetLowerLeft();

        Thread.sleep(3000); // Wait for 3s for user to check

        Boolean betExists = valueBettingBot.checkTopBet();

        if (betExists) {
            // TODO - add OCR functionality
            valueBettingBot.takeTopBetScreenshot();
        }

        while (true) {
            Thread.sleep(1000);
            log.debug("Running ...");
        }
    }
}
