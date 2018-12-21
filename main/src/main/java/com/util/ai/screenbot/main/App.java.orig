package com.util.ai.screenbot.main;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.util.ai.screenbot.input.config.InputHandlerModule;
import com.util.ai.screenbot.output.config.OutputHandlerModule;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.support.config.SupportModule;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private static final String TESSDATA_PATH = "../output-handler/tessdata";

    private static final String LANGUAGE = "eng";

    public static void main(String[] args) throws InterruptedException {
        log.info("Creating Guice injector ...");
        final Injector injector = Guice.createInjector(new SupportModule(), new InputHandlerModule(), new OutputHandlerModule());

        log.info("Creating tesseract ...");
        final TessBaseAPI tesseract = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);

        // register shutdown hook to destroy the tesseract
        log.info("Adding shutdown hook ...");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                log.info("Destroying tesseract ...");
                TesseractAPI.destroyTesseract(tesseract);

                log.info("Service stopped!");
            }
        }));

        final RebelService rebel = injector.getInstance(RebelService.class);

        log.info("Starting the service ...");
        rebel.run();
    }
}
