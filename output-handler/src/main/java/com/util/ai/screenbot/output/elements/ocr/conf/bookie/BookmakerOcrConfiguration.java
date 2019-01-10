package com.util.ai.screenbot.output.elements.ocr.conf.bookie;

import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;

public interface BookmakerOcrConfiguration {

	OcrImageProcessingConf getBalanceOcrConfig();
	
	OcrImageProcessingConf getMaxStakeOcrConfig();

	OcrImageProcessingConf getMinStakeOcrConfig();

	OcrImageProcessingConf getOddsOcrConfig();

}
