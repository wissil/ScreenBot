package com.util.ai.screenbot.output.elements;

public class VBBetInfoStakeElement implements VBScreenElement {

	private final double stake;
	
	public VBBetInfoStakeElement(double stake) {
		this.stake = stake;
	}
	
	public double getStake() {
		return stake;
	}

	@Override
	public String toString() {
		return "VBBetInfoStakeElement [stake=" + stake + "]";
	}
}
