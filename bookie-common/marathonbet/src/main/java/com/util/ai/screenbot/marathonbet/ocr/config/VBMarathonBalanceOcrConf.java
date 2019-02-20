package com.util.ai.screenbot.marathonbet.ocr.config;

import com.util.ai.screenbot.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.ocr.OcrReadMode;


public class VBMarathonBalanceOcrConf implements OcrImageProcessingConf {
	
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
		return OcrReadMode.DIGITS;
	}

	@Override
	public boolean isNegative() {
		return true;
	}

}
