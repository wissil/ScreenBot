package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.logic.marathonbet.MarathonbetInputBot;
import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class MarathonBetHandler extends AbstractBookieHandler {

    private final MarathonbetInputBot marathonBot;

    public MarathonBetHandler(InputHandler in, MarathonbetInputBot marathonBot) {
        super(in);
        this.marathonBot = Objects.requireNonNull(marathonBot);
    }

    @Override
    public void placeBet(double stake) throws InvalidBetSlipException {
        marathonBot.setBetStake(String.valueOf(stake));
        marathonBot.clickBet();
    }

    @Override
    public void removeBet() throws InvalidBetSlipException {
        marathonBot.clickRemoveAll();
    }

    @Override
    public BufferedImage getBookmakerOddsImage() throws InvalidBetSlipException {
        return marathonBot.takeBookmakerOddsScreenshot();
    }

    @Override
    public BufferedImage getMinStakeImage() throws InvalidBetSlipException {
        return marathonBot.takeMinStakeScreenshot();
    }

    @Override
    public BufferedImage getMaxStakeImage() throws InvalidBetSlipException {
        return marathonBot.takeMaxStakeScreenshot();
    }

    @Override
    public BufferedImage getBalanceStakeImage() throws FatalVBException {
        return marathonBot.takeBalanceScreenshot();
    }

    @Override
    public void checkBettingSlip() throws InvalidBetSlipException {
        marathonBot.checkBettingSlip();
    }

    @Override
    public void waitForBettingBrowserToLoad() throws BettingBrowserTimeoutException {
        // Do nothing

    }

}
