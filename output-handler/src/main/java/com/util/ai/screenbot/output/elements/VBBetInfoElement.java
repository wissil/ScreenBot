package com.util.ai.screenbot.output.elements;

public class VBBetInfoElement implements VBScreenElement {
	
	private final double odds;
	
	private final double stake;
	
	private final double value;

	public VBBetInfoElement(double odds, double stake, double value) {
		this.odds = odds;
		this.stake = stake;
		this.value = value;
	}

	public double getOdds() {
		return odds;
	}

	public double getStake() {
		return stake;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "VBBetInfoElement [odds=" + odds + ", stake=" + stake + ", value=" + value + "]";
	}
}
