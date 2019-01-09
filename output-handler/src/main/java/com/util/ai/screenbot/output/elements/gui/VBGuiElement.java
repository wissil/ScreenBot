package com.util.ai.screenbot.output.elements.gui;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.ocr.OcrReadMode;

public interface VBGuiElement {

	BufferedImage getImage();
	
	OcrReadMode getOcrReadMode();
	
	boolean isNegative();
}
