package com.util.ai.screenbot.output.elements;

public class VBOddsInputElement implements VBScreenElement {
	
	private final String odds;
	
	private final String stake;
	
	private final String value;

	public VBOddsInputElement(String odds, String stake, String value) {
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
		return "VBOddsInputElement [odds=" + odds + ", stake=" + stake + ", value=" + value + "]";
	}
}
