package com.util.ai.screenbot.output.elements.gui;

import java.awt.image.BufferedImage;
import java.util.Objects;

public abstract class AbstractVBGuiElement implements VBGuiElement {
	
	private final BufferedImage image;
	
	public AbstractVBGuiElement(BufferedImage image) {
		this.image = Objects.requireNonNull(image);
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}
}
