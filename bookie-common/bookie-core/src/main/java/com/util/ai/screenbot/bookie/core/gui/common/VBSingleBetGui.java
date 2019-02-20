package com.util.ai.screenbot.bookie.core.gui.common;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.bookie.core.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.bookie.core.gui.ocr.config.VBSingleBetOcrConf;
import com.util.ai.screenbot.ocr.OcrImageProcessingConf;

public class VBSingleBetGui extends AbstractVBGuiElement {

	public VBSingleBetGui(BufferedImage image) {
		super(image);
	}

	@Override
	public OcrImageProcessingConf getConf() {
		return new VBSingleBetOcrConf();
	}
}
