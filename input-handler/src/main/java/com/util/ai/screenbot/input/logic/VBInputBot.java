package com.util.ai.screenbot.input.logic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.AbstractVBConstants;
import com.util.ai.screenbot.input.constants.VBConstants_1600x900;
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

    public VBInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
        this.screenHandler = Objects.requireNonNull(screenHandler);
        this.keyboardHandler = Objects.requireNonNull(keyboardHandler);
        this.mouseHandler = Objects.requireNonNull(mouseHandler);
        this.vbConstants = Objects.requireNonNull(vbConstants);
    }

    public Boolean isValueBettingInForeground() {
        String currentWindowName = screenHandler.getActiveWindow().getName();
        log.info("Current app: " + currentWindowName);
        return currentWindowName.trim().toLowerCase().startsWith(VBConstants_1600x900.VALUE_BETTING_APP_PREFIX.toLowerCase());

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

        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetUpperCornerHeight());

        log.debug("Coef: " + ScreenConfig.screenCoef);
        log.debug("topBetUpperLeftX: " + betX);
        log.debug("topBetUpperLeftY: " + betY);

        mouseHandler.moveMouse(betX, betY);
    }

    public void navigateToTopBetLowerLeft() {

        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetLowerCornerHeight());

        log.debug("topBetLowerLeftX: " + betX);
        log.debug("topBetLowerLeftY: " + betY);

        mouseHandler.moveMouse(betX, betY);
    }

    public Boolean checkTopBet() {

        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetMiddleWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetMiddleHeight());

        log.debug("topBetMiddleX: " + betX);
        log.debug("topBetMiddleY: " + betY);

        mouseHandler.moveMouse(betX, betY);
        mouseHandler.leftClick();

        Color color = screenHandler.detectColor(betX, betY);

        Boolean topBetExists = !color.equals(Color.WHITE);

        log.debug("Top bet exists? --> " + topBetExists);
        return topBetExists;
    }

    public BufferedImage takeTopBetScreenshot() {

        Integer betX = (int) (appDimensions.x + Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

        Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetUpperCornerHeight());

        log.debug("topBetMiddleX: " + betX);
        log.debug("topBetMiddleY: " + betY);

        BufferedImage image = screenHandler.takeScreenshot(betX, betY, (int) Math.round(ScreenConfig.width * vbConstants.getBetScreenshotWidth()),
                (int) Math.round(ScreenConfig.height * vbConstants.getBetScreenshotHeight()));

        return image;

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
