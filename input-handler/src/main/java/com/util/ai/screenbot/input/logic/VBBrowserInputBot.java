package com.util.ai.screenbot.input.logic;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.AbstractVBConstants;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public class VBBrowserInputBot extends VBInputBot {

    protected static final Logger log = LoggerFactory.getLogger(VBBrowserInputBot.class);

    private Rectangle browserDimensions;

    public VBBrowserInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler, AbstractVBConstants vbConstants) {
        super(keyboardHandler, screenHandler, mouseHandler, vbConstants);
    }

    public void initializeBettingBrowser() {
        initialize(AbstractVBConstants.VALUE_BETTING_BROWSER_PREFIX);

        // Initialize Betting Browser screen dimensions
        this.browserDimensions = checkScreen();
    }

    public BufferedImage takeOddsScreenshot() {
        Integer betX = (int) (browserDimensions.x + Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

        Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getOddsInfoHeight());

        mouseHandler.moveMouse(betX, betY);

        return null;
    }

    public BufferedImage takeStakeScreenshot() {
        Integer betX = (int) (browserDimensions.x + Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

        Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getStakeInfoHeight());

        mouseHandler.moveMouse(betX, betY);
        return null;
    }

    public BufferedImage takeValueScreenshot() {
        Integer betX = (int) (browserDimensions.x + Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

        Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getValueInfoHeight());

        mouseHandler.moveMouse(betX, betY);
        return null;
    }

    public void clickCancel() {
        Integer betX = (int) (browserDimensions.x + Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getCancelButtonWidth()));

        Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getBrowserButtonsHeight());

        mouseHandler.moveMouse(betX, betY);

        // mouseHandler.leftClick(); - FIXME
    }

    public void clickConfirm() {
        Integer betX = (int) (browserDimensions.x + Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getConfirmButtonWidth()));

        Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getBrowserButtonsHeight());

        mouseHandler.moveMouse(betX, betY);

        mouseHandler.leftClick();
    }

    public void clickConfirmOk() {
        Integer betX = (int) (browserDimensions.x + Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getConfirmOkButtonWidth()));

        Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getConfirmOkButtonHeight());

        mouseHandler.moveMouse(betX, betY);

        // mouseHandler.leftClick(); - FIXME
    }
}
