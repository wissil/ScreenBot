package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.logic.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.VBMainInputBot;
import com.util.ai.screenbot.main.bookie.Bookie;

public class InputHandlerImpl implements InputHandler {

    private final VBMainInputBot mainBot;

    private final VBBrowserInputBot browserBot;

    public InputHandlerImpl(VBMainInputBot mainBot, VBBrowserInputBot browserBot) {
        this.mainBot = Objects.requireNonNull(mainBot);
        this.browserBot = Objects.requireNonNull(browserBot);
    }

    @Override
    public boolean isNewBetPresent() {
        return mainBot.checkTopBet();
    }

    @Override
    public BufferedImage getSingleBetImage() {
        return mainBot.takeTopBetScreenshot();
    }

    @Override
    public void openBettingBrowserWindow() {
        browserBot.initializeBettingBrowser();
    }

    @Override
    public void openMainWindow() {
        mainBot.initializeValueBetting();
    }

    @Override
    public void removeTopBet() {
        mainBot.removeTopBet();
    }

    @Override
    public void placeBet(Bookie bookie, double stake) {
        bookie.getHandler().placeBet(stake);
    }

    @Override
    public void removeBet(Bookie bookie) {
        bookie.getHandler().removeBet();
    }

    @Override
    public BufferedImage getPlaceBetImage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BufferedImage getOddsInputImage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clickOKAtBettingBrowser() {
        // TODO Auto-generated method stub
    }

    @Override
    public void clickCancelAtBettingBrowser() {
        // TODO Auto-generated method stub
    }

}
