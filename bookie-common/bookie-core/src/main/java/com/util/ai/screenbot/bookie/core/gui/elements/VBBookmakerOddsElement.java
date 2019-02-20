package com.util.ai.screenbot.bookie.core.gui.elements;

public class VBBookmakerOddsElement implements VBScreenElement {

	private final String odds;
	
	public VBBookmakerOddsElement(String odds) {
		this.odds = odds;
	}
	
	public String getOdds() {
		return odds;
	}

	@Override
	public String toString() {
		return "VBBookmakerOddsElement [odds=" + odds + "]";
	}
}
