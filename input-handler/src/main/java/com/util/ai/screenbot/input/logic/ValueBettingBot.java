package com.util.ai.screenbot.input.logic;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.ValueBettingConstants;
import com.util.ai.screenbot.input.exceptions.ValueBettingAppException;
import com.util.ai.screenbot.input.utils.KeyboardHandler;
import com.util.ai.screenbot.input.utils.ScreenHandler;

public class ValueBettingBot {

    private static final Logger log = LoggerFactory.getLogger(ValueBettingBot.class);

    private final Integer MAX_NUMBER_OF_HOPS = 10;

    private ScreenHandler screenHandler;

    private KeyboardHandler keyboardHandler;

    public ValueBettingBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler) {
        this.screenHandler = Objects.requireNonNull(screenHandler);
        this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
    }

    public Boolean isValueBettingInForeground() {
        String currentWindowName = screenHandler.getCurrentWindowName();
        log.info("Current app: " + currentWindowName);
        return currentWindowName.trim().toLowerCase().startsWith(ValueBettingConstants.VALUE_BETTING_APP_PREFIX.toLowerCase());

    }

    public void switchToValueBetting() {

        // If Value Betting is in the foreground do nothing
        if (!isValueBettingInForeground()) {

            Integer numberOfHops = 1;

            // While Value Betting is NOT the foreground app and number of switches is lower than max allowed, switch the active
            // app
            while (!isValueBettingInForeground() && numberOfHops <= MAX_NUMBER_OF_HOPS) {

                log.info("Switching current app...");

                keyboardHandler.pressSwitchApp(numberOfHops);
                numberOfHops++;

                // Otherwise it is too fast and only 'Task Switching' app is recognized
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Do nothing
                }
            }

            // If Value Betting is still NOT the foreground app throw the exception
            if (!isValueBettingInForeground()) {
                throw new ValueBettingAppException("Value betting is not started");
            }
        }
    }
}
