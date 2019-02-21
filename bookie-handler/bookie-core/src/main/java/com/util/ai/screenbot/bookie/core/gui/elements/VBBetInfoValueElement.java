package com.util.ai.screenbot.bookie.core.gui.elements;

public class VBBetInfoValueElement implements VBScreenElement {

	private final double value;
	
	public VBBetInfoValueElement(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "VBBetInfoValueElement [value=" + value + "]";
	}
}
