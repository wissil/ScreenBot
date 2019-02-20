package com.util.ai.screenbot.bookie.core.gui.ocr.config;

import com.util.ai.screenbot.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.ocr.OcrReadMode;

public class VBSingleBetOcrConf implements OcrImageProcessingConf {
	
	private static final int SCALE = 5;
	
	private static final int WHITE_TRESHOLD = 120;
	
	@Override
	public int SCALE() {
		return SCALE;
	}

	@Override
	public int WHITE_TRESHOLD() {
		return WHITE_TRESHOLD;
	}

	@Override
	public OcrReadMode OCR_READ_MODE() {
		return OcrReadMode.ENGLISH;
	}

	@Override
	public boolean isNegative() {
		return true;
	}

}
