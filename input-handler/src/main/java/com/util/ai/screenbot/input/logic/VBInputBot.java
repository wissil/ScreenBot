package com.util.ai.screenbot.input.logic;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.AbstractVBConstants;
import com.util.ai.screenbot.input.exceptions.ValueBettingAppException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public abstract class VBInputBot {

    protected static final Logger log = LoggerFactory.getLogger(VBInputBot.class);

    protected final Integer MAX_NUMBER_OF_HOPS = 10;

    protected ScreenHandler screenHandler;

    protected KeyboardHandler keyboardHandler;

    protected MouseHandler mouseHandler;

    protected AbstractVBConstants vbConstants;

    protected VBInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
        this.screenHandler = Objects.requireNonNull(screenHandler);
        this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
        this.mouseHandler = Objects.requireNonNull(mouseHandler);
        this.vbConstants = Objects.requireNonNull(vbConstants);
    }

    protected void initialize(String windowName) {

        // If Value Betting is in the foreground do nothing
        if (!isWindowInForeground(windowName)) {

            Integer numberOfHops = 1;

            // While Value Betting is NOT the foreground app and number of switches is lower than max allowed, switch the active
            // app
            while (!isWindowInForeground(windowName) && numberOfHops <= MAX_NUMBER_OF_HOPS) {

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
            if (!isWindowInForeground(windowName)) {
                throw new ValueBettingAppException("Value Betting is not started");
            }

        }
    }

    public Boolean isWindowInForeground(String windowName) {
        String currentWindowName = screenHandler.getActiveWindow().getName();
        log.info("Current app: " + currentWindowName);
        return currentWindowName.trim().toLowerCase().startsWith(windowName.toLowerCase());

    }

    protected Rectangle checkScreen() {
        Rectangle screen = screenHandler.getActiveWindow().getBounds();

        log.debug("VB screen width: " + screen.width);
        log.debug("VB screen height: " + screen.height);

        // App works only if Value Betting window is full screened
        checkIsWindowFullScreen(screen);

        return screen;
    }

    protected void checkIsWindowFullScreen(Rectangle screen) {

        // If full screen Value Betting app screen width should not be lower than monitor screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        log.debug("Screen width:" + screenSize.getWidth());
        if (screen.getWidth() < screenSize.getWidth()) {
            throw new ValueBettingAppException("Value Betting app should be started in full screen");
        }
    }

    public void write(String text) {
        this.keyboardHandler.write(text);
    }

    protected class BetCoordinates {
        public Integer x;
        public Integer y;

        public BetCoordinates(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

    }

}
