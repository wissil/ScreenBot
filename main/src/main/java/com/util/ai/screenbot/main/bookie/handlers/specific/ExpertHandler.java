package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.logic.expert.ExpertInputBot;
import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class ExpertHandler extends AbstractBookieHandler {

    private final ExpertInputBot expertBot;

    public ExpertHandler(InputHandler in, ExpertInputBot expertBot) {
        super(in);
        this.expertBot = Objects.requireNonNull(expertBot);
    }

    @Override
    public void placeBet(double stake) throws InvalidBetSlipException {
        expertBot.setBetStake(String.valueOf(stake));
        expertBot.clickBet();
    }

    @Override
    public void removeBet() throws InvalidBetSlipException {
        expertBot.clickRemoveAll();

    }

    @Override
    public BufferedImage getBookmakerOddsImage() throws InvalidBetSlipException {
        return expertBot.takeBookmakerOddsScreenshot();
    }

    @Override
    public BufferedImage getMinStakeImage() {
        // Does not exists in Expert
        return null;
    }

    @Override
    public BufferedImage getMaxStakeImage() {
        // Does not exists in Expert
        return null;
    }

    @Override
    public BufferedImage getBalanceStakeImage() throws FatalVBException {
        return expertBot.takeBalanceScreenshot();
    }

    @Override
    public void checkBettingSlip() throws InvalidBetSlipException {
        expertBot.checkBettingSlip(5);

    }

}
