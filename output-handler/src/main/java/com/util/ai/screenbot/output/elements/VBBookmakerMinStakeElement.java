package com.util.ai.screenbot.output.elements;

public class VBBookmakerMinStakeElement implements VBScreenElement {
	
	private final double stake;
	
	public VBBookmakerMinStakeElement(double stake) {
		this.stake = stake;
	}
	
	public double getStake() {
		return stake;
	}

	@Override
	public String toString() {
		return "VBBookmakerMinStakeElement [stake=" + stake + "]";
	}
}
