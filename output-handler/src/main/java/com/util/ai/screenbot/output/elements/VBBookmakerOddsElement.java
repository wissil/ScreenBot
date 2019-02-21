package com.util.ai.screenbot.output.elements;

public class VBBookmakerOddsElement implements VBScreenElement {

	private final double odds;
	
	public VBBookmakerOddsElement(double odds) {
		this.odds = odds;
	}
	
	public double getOdds() {
		return odds;
	}

	@Override
	public String toString() {
		return "VBBookmakerOddsElement [odds=" + odds + "]";
	}
}
