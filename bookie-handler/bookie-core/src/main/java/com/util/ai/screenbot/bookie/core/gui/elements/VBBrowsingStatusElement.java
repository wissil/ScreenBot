package com.util.ai.screenbot.bookie.core.gui.elements;

public class VBBrowsingStatusElement implements VBScreenElement {

	private final boolean isDone;
	
	public VBBrowsingStatusElement(boolean isDone) {
		this.isDone = isDone;
	}
	
	public boolean isDone() {
		return isDone;
	}

	@Override
	public String toString() {
		return "VBBrowsingStatusElement [isDone=" + isDone + "]";
	}
}
