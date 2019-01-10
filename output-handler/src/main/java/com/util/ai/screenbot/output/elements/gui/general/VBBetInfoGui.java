package com.util.ai.screenbot.output.elements.gui.general;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.output.elements.ocr.conf.general.VBBetInfoOcrConf;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;

public class VBBetInfoGui extends AbstractVBGuiElement {

	public VBBetInfoGui(BufferedImage image) {
		super(image);
	}

	@Override
	public OcrImageProcessingConf getConf() {
		return new VBBetInfoOcrConf();
	}

}
