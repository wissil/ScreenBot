package com.util.ai.screenbot.output.elements.gui.general;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.output.ocr.OcrReadMode;

public class VBSingleBetGui extends AbstractVBGuiElement {

	public VBSingleBetGui(BufferedImage image) {
		super(image);
	}

	@Override
	public OcrReadMode getOcrReadMode() {
		return OcrReadMode.ENGLISH;
	}

	@Override
	public boolean isNegative() {
		return true;
	}

}
