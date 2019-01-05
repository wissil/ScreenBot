package com.util.ai.screenbot.main.bookie.handlers;

public interface BookieHandler {

	void placeBet(double stake);

	void removeBet();

	boolean isBetCorrect(double stake);
}
