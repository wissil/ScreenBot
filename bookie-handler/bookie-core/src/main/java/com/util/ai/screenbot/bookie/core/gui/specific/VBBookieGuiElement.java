package com.util.ai.screenbot.bookie.core.gui.specific;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.bookie.core.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.ocr.OcrImageProcessingConf;


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
