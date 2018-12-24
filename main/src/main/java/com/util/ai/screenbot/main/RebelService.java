package com.util.ai.screenbot.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.util.ai.screenbot.main.automata.VBStateMachine;

public class RebelService {

    private static final Logger log = LoggerFactory.getLogger(RebelService.class);

    @Inject
    private VBStateMachine machine;

    public void run() throws InterruptedException {
        log.info("Service started successfully!");

        machine.run();
    }

}
