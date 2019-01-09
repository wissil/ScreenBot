package com.util.ai.screenbot.output.elements.gui.bookie;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.elements.gui.AbstractVBGuiElement;
import com.util.ai.screenbot.output.ocr.OcrReadMode;

public class VBBalanceGui extends AbstractVBGuiElement {

	public VBBalanceGui(BufferedImage image) {
		super(image);
	}

	@Override
	public OcrReadMode getOcrReadMode() {
		return OcrReadMode.DIGITS;
	}

	@Override
	public boolean isNegative() {
		return true;
	}

}
