package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.logic.williamhill.WilliamHillInputBot;
import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class WilliamHillHandler extends AbstractBookieHandler {

    private final WilliamHillInputBot williamHillBot;

    public WilliamHillHandler(InputHandler in, WilliamHillInputBot williamHillBot) {
        super(in);
        this.williamHillBot = Objects.requireNonNull(williamHillBot);
    }

    @Override
    public void placeBet(double stake) throws InvalidBetSlipException {
        williamHillBot.setBetStake(String.valueOf(stake));
        williamHillBot.clickBet();
    }

    @Override
    public void removeBet() throws InvalidBetSlipException {
        williamHillBot.clickRemoveAll();

    }

    @Override
    public BufferedImage getBookmakerOddsImage() throws InvalidBetSlipException {
        return williamHillBot.takeBookmakerOddsScreenshot();
    }

    @Override
    public BufferedImage getMinStakeImage() {
        // Does not exists in William Hill
        return null;
    }

    @Override
    public BufferedImage getMaxStakeImage() {
        // Does not exists in William Hill
        return null;
    }

    @Override
    public BufferedImage getBalanceStakeImage() throws FatalVBException {
        return williamHillBot.takeBalanceScreenshot();
    }

    @Override
    public void checkBettingSlip() throws InvalidBetSlipException {
        williamHillBot.checkBettingSlip();

    }

}
