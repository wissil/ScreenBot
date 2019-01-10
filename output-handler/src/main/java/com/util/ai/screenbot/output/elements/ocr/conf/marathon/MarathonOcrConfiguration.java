package com.util.ai.screenbot.output.elements.ocr.conf.marathon;

import com.util.ai.screenbot.output.elements.ocr.conf.bookie.BookmakerOcrConfiguration;
import com.util.ai.screenbot.output.ocr.OcrImageProcessingConf;

public class MarathonOcrConfiguration implements BookmakerOcrConfiguration {

	@Override
	public OcrImageProcessingConf getBalanceOcrConfig() {
		return new VBMarathonBalanceOcrConf();
	}

	@Override
	public OcrImageProcessingConf getMaxStakeOcrConfig() {
		return new VBMarathonMaxStakeOcrConf();
	}

	@Override
	public OcrImageProcessingConf getMinStakeOcrConfig() {
		return new VBMarathonMinStakeOcrConf();
	}

	@Override
	public OcrImageProcessingConf getOddsOcrConfig() {
		return new VBMarathonOddsOcrConf();
	}

}
