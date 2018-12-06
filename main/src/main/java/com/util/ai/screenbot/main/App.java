package com.util.ai.screenbot.main;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.output.ocr.TesseractAPI;

/**
 * Hello world!
 *
 */
public class App {
	
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final String TESSDATA_PATH = "../output-handler/tessdata";
	
	private static final String LANGUAGE = "eng";
	
    public static void main(String[] args) throws InterruptedException {
    		final TessBaseAPI tesseract = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
    	
    		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				
				@Override
				public void run() {
					TesseractAPI.destroyTesseract(tesseract);
				}
			}));
    		
    		while (true) {
    			Thread.sleep(1000);
    			logger.debug("Running ... still ...");
//    			System.out.println("Running ...");
    		}
    }
}
