package com.util.ai.screenbot.output.elements;

public class VBBookmakerStakeMinElement implements VBScreenElement {
	
	private final double stake;
	
	public VBBookmakerStakeMinElement(double stake) {
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
