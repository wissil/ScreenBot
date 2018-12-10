package com.util.ai.screenbot.input.handlers.util;

import java.awt.Rectangle;

public class ActiveWindow {

	private final String name;

	private final Rectangle bounds;

	public ActiveWindow(String name, Rectangle bounds) {
		this.name = name;
		this.bounds = bounds;
	}

	public String getName() {
		return name;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	@Override 
	public String toString() { 
		return String.format("[x=%d, y=%d; width=%d, height=%d]", 
				bounds.x, bounds.y, bounds.width, bounds.height);
	} 
}
