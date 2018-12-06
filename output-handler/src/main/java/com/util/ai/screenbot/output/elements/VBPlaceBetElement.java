package com.util.ai.screenbot.output.elements;

public class VBPlaceBetElement implements VBScreenElement {

	private final String odds;
	
	public VBPlaceBetElement(String odds) {
		this.odds = odds;
	}
	
	public String getOdds() {
		return odds;
	}

	@Override
	public String toString() {
		return "VBPlaceBetScreenElement [odds=" + odds + "]";
	}
}
