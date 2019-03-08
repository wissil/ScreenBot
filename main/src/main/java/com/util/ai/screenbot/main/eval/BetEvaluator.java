package com.util.ai.screenbot.main.eval;

public class BetEvaluator {

	private static final double EPS = 10e-5;

	private BetEvaluator() {
	}

	public static boolean shouldPlaceBet(double oddsLimit, double oddsActual, double stake, double balance) {
		// check if odds are greater than the limit
		if (Double.compare(oddsActual, oddsLimit) < 0)
			return false;

		if (balance - stake < EPS)
			return false; // check that balance > stake

		return true;
	}
}
