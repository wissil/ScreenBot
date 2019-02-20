package com.util.ai.screenbot.ocr;

import java.awt.image.BufferedImage;

public interface OcrImageProcessor {

	BufferedImage process(BufferedImage image, OcrImageProcessingConf conf);
}
