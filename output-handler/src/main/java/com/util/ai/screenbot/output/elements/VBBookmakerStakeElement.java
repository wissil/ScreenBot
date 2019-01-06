package com.util.ai.screenbot.output.elements;

public class VBBookmakerStakeElement implements VBScreenElement {
	
	private final double stake;
	
	public VBBookmakerStakeElement(double stake) {
		this.stake = stake;
	}
	
	public double getStake() {
		return stake;
	}

	@Override
	public String toString() {
		return "VBBookmakerStakeElement [stake=" + stake + "]";
	}
}
