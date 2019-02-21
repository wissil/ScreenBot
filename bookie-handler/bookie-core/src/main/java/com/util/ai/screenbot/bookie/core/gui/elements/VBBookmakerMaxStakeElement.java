package com.util.ai.screenbot.bookie.core.gui.elements;

public class VBBookmakerMaxStakeElement implements VBScreenElement {
	
	private final double stake;
	
	public VBBookmakerMaxStakeElement(double stake) {
		this.stake = stake;
	}
	
	public double getStake() {
		return stake;
	}

	@Override
	public String toString() {
		return "VBBookmakerMaxStakeElement [stake=" + stake + "]";
	}
}
