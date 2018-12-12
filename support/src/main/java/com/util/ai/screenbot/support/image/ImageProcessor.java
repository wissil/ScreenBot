package com.util.ai.screenbot.support.image;

import java.awt.image.BufferedImage;

public interface ImageProcessor {

	BufferedImage process(BufferedImage image, boolean negative);
}
