package com.util.ai.screenbot.output.elements.gui.general;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.output.ocr.OcrReadMode;

public class VBBrowsingStatusGui extends AbstractVBGuiElement {

	public VBBrowsingStatusGui(BufferedImage image) {
		super(image);
	}

	@Override
	public OcrReadMode getOcrReadMode() {
		return OcrReadMode.ENGLISH;
	}

	@Override
	public boolean isNegative() {
		return false;
	}

}
