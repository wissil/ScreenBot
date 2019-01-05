package com.util.ai.screenbot.main.bookie.handlers.specific;

import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class Bet365Handler extends AbstractBookieHandler {

	public Bet365Handler(InputHandler in) {
		super(in);
	}

	@Override
	public void placeBet(double stake) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBet() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBetCorrect(double stake) {
		// TODO Auto-generated method stub
		return false;
	}
}
