package com.util.ai.screenbot.output.elements.gui.bookie;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.output.elements.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;

public abstract class VBBookieGuiElement extends AbstractVBGuiElement {
	
	private final OcrImageProcessingConf conf;

	public VBBookieGuiElement(BufferedImage image, OcrImageProcessingConf conf) {
		super(image);
		this.conf = Objects.requireNonNull(conf);
	}

	@Override
	public OcrImageProcessingConf getConf() {
		return conf;
	}
}
