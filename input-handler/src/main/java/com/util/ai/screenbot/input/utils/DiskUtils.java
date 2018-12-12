package com.util.ai.screenbot.input.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskUtils {

    private static final Logger log = LoggerFactory.getLogger(DiskUtils.class);

    public static void saveBetToDisk(BufferedImage image) {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        File outputDir = new File("./external/singleBets/");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        try {
            ImageIO.write(image, "png", new File(outputDir, dt.format(new Date()) + ".png"));
        } catch (IOException e) {
            log.error("Not able to save image to disk", e);
        }
    }
}
