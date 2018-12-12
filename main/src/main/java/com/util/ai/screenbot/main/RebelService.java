package com.util.ai.screenbot.main;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.logic.VBInputBot;
import com.util.ai.screenbot.input.utils.DiskUtils;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.logic.VBOutputInterpreter;
import com.util.ai.screenbot.output.parsing.exceptions.VBElementInterpretationException;

public class RebelService {

    @Inject
    private VBInputBot valueBettingBot;

    @Inject
    private VBOutputInterpreter vBOutputInterpreter;

    private TessBaseAPI tesseract;

    private static final Logger log = LoggerFactory.getLogger(RebelService.class);

    public void run() throws InterruptedException {
        log.info("Service started successfully!");

        log.debug("Screen resolution: " + ScreenConfig.resolution);

        valueBettingBot.initialize();

        valueBettingBot.navigateToTopBetUpperLeft();

        Thread.sleep(3000); // Wait for 3s for user to check

        valueBettingBot.navigateToTopBetLowerLeft();

        Thread.sleep(3000); // Wait for 3s for user to check

        Boolean betExists = valueBettingBot.checkTopBet();

        if (betExists) {

            BufferedImage topBetScreenShot = valueBettingBot.takeTopBetScreenshot();
            DiskUtils.saveBetToDisk(topBetScreenShot); // For debug purposes

            VBSingleBetElement singleBetElement = null;
            try {

                singleBetElement = vBOutputInterpreter.interpretSingleBet(tesseract, topBetScreenShot);
            } catch (VBElementInterpretationException e) {

                // FIXME
                // Log the error
                // Send an email
                // Remove bet from betting list
                e.printStackTrace();
            }

            System.out.println("Top bet: " + singleBetElement.toString());
        }

        while (true) {
            Thread.sleep(1000);
            log.debug("Running ...");
        }
    }

    public void setTessBaseAPI(TessBaseAPI tesseract) {
        this.tesseract = tesseract;
    }

}
