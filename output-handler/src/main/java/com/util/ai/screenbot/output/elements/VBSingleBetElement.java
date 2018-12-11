package com.util.ai.screenbot.output.elements;


public class VBSingleBetElement implements VBScreenElement {

	private final String value;
	
	private final String stake;
	
	private final String participants;
	
	private final String outcome;
	
	private final String odds;
	
	private final String bookie;

	public VBSingleBetElement(String value, String stake, String participants, String outcome, String odds, String bookie) {
		this.value = value;
		this.stake = stake;
		this.participants = participants;
		this.outcome = outcome;
		this.odds = odds;
		this.bookie = bookie;
	}

	public String getValue() {
		return value;
	}

	public String getStake() {
		return stake;
	}

	public String getParticipants() {
		return participants;
	}

	public String getOutcome() {
		return outcome;
	}

	public String getOdds() {
		return odds;
	}

	public String getBookie() {
		return bookie;
	}

	@Override
	public String toString() {
		return "VBItemElement [value=" + value + ", stake=" + stake + ", participants=" + participants + ", outcome="
				+ outcome + ", odds=" + odds + ", bookie=" + bookie + "]";
	}	
}
