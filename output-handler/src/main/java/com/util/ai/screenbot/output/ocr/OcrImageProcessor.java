package com.util.ai.screenbot.output.ocr;

import java.awt.image.BufferedImage;

public interface OcrImageProcessor {

	BufferedImage process(BufferedImage image, OcrImageProcessingConf conf);
}
