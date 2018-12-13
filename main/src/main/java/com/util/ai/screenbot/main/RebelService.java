package com.util.ai.screenbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RebelService {

    private static final Logger log = LoggerFactory.getLogger(RebelService.class);

    public void run() throws InterruptedException {
        log.info("Service started successfully!");

        while (true) {
            Thread.sleep(1000);
            log.debug("Running ...");
        }
    }

}
