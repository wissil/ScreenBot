package com.util.ai.screenbot.output.elements.gui.general;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.output.elements.ocr.conf.general.VBSingleBetOcrConf;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;

public class VBSingleBetGui extends AbstractVBGuiElement {

	public VBSingleBetGui(BufferedImage image) {
		super(image);
	}

	@Override
	public OcrImageProcessingConf getConf() {
		return new VBSingleBetOcrConf();
	}
}
