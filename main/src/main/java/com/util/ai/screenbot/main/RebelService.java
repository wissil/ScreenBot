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

        while (true) {
            Thread.sleep(1000);
            log.debug("Running ...");
        }
    }
}
