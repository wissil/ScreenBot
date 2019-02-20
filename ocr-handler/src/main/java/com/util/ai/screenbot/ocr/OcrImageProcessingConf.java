package com.util.ai.screenbot.ocr;

public interface OcrImageProcessingConf {

	int SCALE();
	
	int WHITE_TRESHOLD();
		
	OcrReadMode OCR_READ_MODE();
	
	boolean isNegative();
}
