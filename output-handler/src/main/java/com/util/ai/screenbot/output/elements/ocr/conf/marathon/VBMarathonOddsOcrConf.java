package com.util.ai.screenbot.output.elements.ocr.conf.marathon;

import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.output.ocr.OcrReadMode;

public class VBMarathonOddsOcrConf implements OcrImageProcessingConf {

	private static final int SCALE = 5;

	private static final int WHITE_TRESHOLD = 170;

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
		return false;
	}

}
