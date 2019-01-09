package com.util.ai.screenbot.output.elements;


public class VBSingleBetElement implements VBScreenElement {

	private final double value;
	
	private final double stake;
	
	private final String participants;
	
	private final String outcome;
	
	private final double odds;
	
	private final String bookie;

	public VBSingleBetElement(double value, double stake, String participants, String outcome, double odds, String bookie) {
		this.value = value;
		this.stake = stake;
		this.participants = participants;
		this.outcome = outcome;
		this.odds = odds;
		this.bookie = bookie;
	}

	public double getValue() {
		return value;
	}

	public double getStake() {
		return stake;
	}

	public String getParticipants() {
		return participants;
	}

	public String getOutcome() {
		return outcome;
	}

	public double getOdds() {
		return odds;
	}

	public String getBookie() {
		return bookie;
	}

	@Override
	public String toString() {
		return "VBSingleBetElement [value=" + value + ", stake=" + stake + ", participants=" + participants + ", outcome="
				+ outcome + ", odds=" + odds + ", bookie=" + bookie + "]";
	}	
}
