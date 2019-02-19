package com.util.ai.screenbot.output.elements;


public class VBSingleBetElement implements VBScreenElement {
	
	private final String participants;
			
	private final String bookie;

	public VBSingleBetElement(String participants, String bookie) {
		this.participants = participants;
		this.bookie = bookie;
	}

	public String getParticipants() {
		return participants;
	}

	public String getBookie() {
		return bookie;
	}

	@Override
	public String toString() {
		return "VBSingleBetElement [participants=" + participants + ", bookie=" + bookie + "]";
	}
}
