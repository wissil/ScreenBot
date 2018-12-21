package com.util.ai.screenbot.input.logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.AbstractVBConstants;
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

    private AbstractVBConstants vbConstants;

    private Rectangle appDimensions;

    private Rectangle browserDimensions;

    public VBInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
        this.screenHandler = Objects.requireNonNull(screenHandler);
        this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
        this.mouseHandler = Objects.requireNonNull(mouseHandler);
        this.vbConstants = Objects.requireNonNull(vbConstants);
    }

    public Boolean isWindowInForeground(String windowName) {
        String currentWindowName = screenHandler.getActiveWindow().getName();
        log.info("Current app: " + currentWindowName);
        return currentWindowName.trim().toLowerCase().startsWith(windowName.toLowerCase());

    }

    private void initialize(String windowName) {

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

    private Rectangle checkScreen() {
        Rectangle screen = screenHandler.getActiveWindow().getBounds();

        log.debug("VB screen width: " + screen.width);
        log.debug("VB screen height: " + screen.height);

        // App works only if Value Betting window is full screened
        checkIsWindowFullScreen(screen);

        return screen;
    }

    public void initializeValueBetting() {
        initialize(AbstractVBConstants.VALUE_BETTING_APP_PREFIX);

        // Initialize Value Betting screen dimensions
        this.appDimensions = checkScreen();
    }

    public void initializeBettingBrowser() {
        initialize(AbstractVBConstants.VALUE_BETTING_BROWSER_PREFIX);

        // Initialize Betting Browser screen dimensions
        this.browserDimensions = checkScreen();
    }

    public void navigateToTopBetUpperLeft() {

        BetCoordinates betCoordinates = getTopBetTopLeftCornerCoordinates();

        mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
    }

    public void navigateToTopBetLowerLeft() {

        BetCoordinates betCoordinates = getTopBetLowerLeftCornerCoordinates();

        mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
    }

    public Boolean checkTopBet() {

        BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

        mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
        mouseHandler.leftClick();

        Color color = screenHandler.detectColor(betCoordinates.x, betCoordinates.y);

        Boolean topBetExists = !color.equals(Color.WHITE);

        log.debug("Top bet exists? --> " + topBetExists);
        return topBetExists;
    }

    public BufferedImage takeTopBetScreenshot() {

        BetCoordinates betCoordinates = getTopBetTopLeftCornerCoordinates();

        BufferedImage image = screenHandler.takeScreenshot(betCoordinates.x, betCoordinates.y,
                (int) Math.round(ScreenConfig.width * vbConstants.getBetScreenshotWidth()),
                (int) Math.round(ScreenConfig.height * vbConstants.getBetScreenshotHeight()));

        return image;

    }

    public BufferedImage takeOddsScreenshot() {
        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getInfoWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getOddsInfoHeight());

        mouseHandler.moveMouse(betX, betY);

        return null;
    }

    public BufferedImage takeStakeScreenshot() {
        return null;
    }

    public BufferedImage takeValueScreenshot() {
        return null;
    }

    /**
     * Place mouse cursor in the middle of top bet. Right click. Wait. Left click
     */
    public void betOnTopBet() {
        BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

        mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
        mouseHandler.leftClick();

        mouseHandler.rightClick();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // Do nothing
        }
        mouseHandler.leftClick();
    }

    /**
     * Place mouse cursor in the middle of top bet. Right click. Move it a bit lower and then right. Left click afterwards.
     */
    public void removeTopBet() {

        BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

        mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
        mouseHandler.leftClick();

        mouseHandler.rightClick();
        Point p = MouseInfo.getPointerInfo().getLocation();

        int newY = p.y + (int) Math.round(ScreenConfig.height * vbConstants.getRemoveBetMouseMovementHeight());

        mouseHandler.moveMouse(p.x, newY);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Do nothing
        }

        int newX = p.x + (int) Math.round(ScreenConfig.width * vbConstants.getRemoveBetMouseMovementWidth());

        mouseHandler.moveMouse(newX, newY);

        // mouseHandler.leftClick(); FIXME - test purposes
    }

    private BetCoordinates getTopBetMiddleCoordinates() {
        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetMiddleWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetMiddleHeight());

        log.debug("topBetMiddleX: " + betX);
        log.debug("topBetMiddleY: " + betY);

        return new BetCoordinates(betX, betY);
    }

    private BetCoordinates getTopBetTopLeftCornerCoordinates() {
        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetUpperCornerHeight());

        log.debug("topBetLeftUpperCornerX: " + betX);
        log.debug("topBetLeftUpperCornerY: " + betY);

        return new BetCoordinates(betX, betY);
    }

    private BetCoordinates getTopBetLowerLeftCornerCoordinates() {
        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetLowerCornerHeight());

        log.debug("topBetLeftLowerCornerX: " + betX);
        log.debug("topBetLeftLowerCornerY: " + betY);

        return new BetCoordinates(betX, betY);
    }

    private void checkIsWindowFullScreen(Rectangle screen) {

        // If full screen Value Betting app screen width should not be lower than monitor screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        log.debug("Screen width:" + screenSize.getWidth());
        if (screen.getWidth() < screenSize.getWidth()) {
            throw new ValueBettingAppException("Value Betting app should be started in full screen");
        }
    }

    private class BetCoordinates {
        public Integer x;
        public Integer y;

        public BetCoordinates(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

    }
}
