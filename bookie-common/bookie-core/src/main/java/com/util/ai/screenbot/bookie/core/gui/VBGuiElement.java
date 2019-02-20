package com.util.ai.screenbot.bookie.core.gui;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.ocr.OcrImageProcessingConf;

public interface VBGuiElement {

	BufferedImage getImage();
	
	OcrImageProcessingConf getConf();
}
