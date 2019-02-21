package com.util.ai.screenbot.bookie.core.gui.elements;

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
