package com.util.ai.screenbot.output.elements.ocr.conf.williamhill;

import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;
import com.util.ai.screenbot.output.ocr.OcrReadMode;

public class VBWilliamHillOddsOcrConf implements OcrImageProcessingConf {

	private static final int SCALE = 12;

	private static final int WHITE_TRESHOLD = 210;

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
		return false;
	}

}
