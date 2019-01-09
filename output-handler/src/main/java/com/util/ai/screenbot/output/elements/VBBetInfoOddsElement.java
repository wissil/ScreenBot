package com.util.ai.screenbot.output.elements;

public class VBBetInfoOddsElement implements VBScreenElement {

	private final double odds;
	
	public VBBetInfoOddsElement(double odds) {
		this.odds = odds;
	}
	
	public double getOdds() {
		return odds;
	}

	@Override
	public String toString() {
		return "VBBetInfoOddsElement [odds=" + odds + "]";
	}
}
