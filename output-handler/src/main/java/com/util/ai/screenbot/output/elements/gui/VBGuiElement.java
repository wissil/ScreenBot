package com.util.ai.screenbot.output.elements.gui;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;

public interface VBGuiElement {

	BufferedImage getImage();
	
	OcrImageProcessingConf getConf();
}
