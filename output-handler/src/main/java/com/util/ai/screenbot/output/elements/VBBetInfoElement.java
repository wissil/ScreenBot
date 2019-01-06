package com.util.ai.screenbot.output.elements;

public class VBBetInfoElement implements VBScreenElement {
	
	private final String odds;
	
	private final String stake;
	
	private final String value;

	public VBBetInfoElement(String odds, String stake, String value) {
		this.odds = odds;
		this.stake = stake;
		this.value = value;
	}

	public String getOdds() {
		return odds;
	}

	public String getStake() {
		return stake;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "VBBetInfoElement [odds=" + odds + ", stake=" + stake + ", value=" + value + "]";
	}
}
