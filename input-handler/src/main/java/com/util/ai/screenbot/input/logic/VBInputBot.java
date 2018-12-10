package com.util.ai.screenbot.input.logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.VBConstants;
import com.util.ai.screenbot.input.exceptions.ValueBettingAppException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public class VBInputBot {

    private static final Logger log = LoggerFactory.getLogger(VBInputBot.class);

    private final Integer MAX_NUMBER_OF_HOPS = 10;

    private ScreenHandler screenHandler;

    private KeyboardHandler keyboardHandler;

    private MouseHandler mouseHandler;

    private Rectangle appDimensions;

    public VBInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler) {
        this.screenHandler = Objects.requireNonNull(screenHandler);
        this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
        this.mouseHandler = Objects.requireNonNull(mouseHandler);
    }

    public Boolean isValueBettingInForeground() {
        String currentWindowName = screenHandler.getActiveWindow().getName();
        log.info("Current app: " + currentWindowName);
        return currentWindowName.trim().toLowerCase().startsWith(VBConstants.VALUE_BETTING_APP_PREFIX.toLowerCase());

    }

    public void initialize() {

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
                throw new ValueBettingAppException("Value Betting is not started");
            }

            // Initialize Value Betting screen dimensions
            this.appDimensions = screenHandler.getActiveWindow().getBounds();

            log.debug("VB width: " + appDimensions.width);
            log.debug("VB height: " + appDimensions.height);

            if (this.appDimensions == null) {
                throw new ValueBettingAppException("Not able to determine Value Betting app screen size");
            }

            // App works only if Value Betting is full screened
            checkIsValueBettingFullScreen();
        }
    }

    public void navigateToTopBetUpperLeft() {

        Integer betX = Math.round(appDimensions.width * VBConstants.TOP_BET_CORNER_WIDTH);

        Integer betY = Math.round(appDimensions.height * VBConstants.TOP_BET_UPPER_CORNER_HEIGHT);

        log.debug("topBetUpperLeftX: " + betX);
        log.debug("topBetUpperLeftY: " + betY);

        mouseHandler.moveMouse(betX, betY);
    }

    public void navigateToTopBetLowerLeft() {

        Integer betX = Math.round(appDimensions.width * VBConstants.TOP_BET_CORNER_WIDTH);

        Integer betY = Math.round(appDimensions.height * VBConstants.TOP_BET_LOWER_CORNER_HEIGHT);

        log.debug("topBetLowerLeftX: " + betX);
        log.debug("topBetLowerLeftY: " + betY);

        mouseHandler.moveMouse(betX, betY);
    }

    public Boolean checkTopBet() {

        Integer betX = Math.round(appDimensions.width * VBConstants.TOP_BET_MIDDLE_WIDTH);

        Integer betY = Math.round(appDimensions.height * VBConstants.TOP_BET_MIDDLE_HEIGHT);

        log.debug("topBetMiddleX: " + betX);
        log.debug("topBetMiddleY: " + betY);

        mouseHandler.moveMouse(betX, betY);
        mouseHandler.leftClick();

        Color color = screenHandler.detectColor(betX, betY);

        Boolean topBetExists = !color.equals(Color.WHITE);

        log.debug("Top bet exists? --> " + topBetExists);
        return topBetExists;
    }

    private void checkIsValueBettingFullScreen() {

        // If full screen Value Betting app screen width should not be lower than monitor screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        log.debug("Screen width:" + screenSize.getWidth());
        if (this.appDimensions.getWidth() < screenSize.getWidth()) {
            throw new ValueBettingAppException("Value Betting app should be started in full screen");
        }
    }
}
