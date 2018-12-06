package com.util.ai.screenbot.output.ocr;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.util.ai.screenbot.output.ocr.exceptions.OCRException;

public class TesseractAPI {
	
	private static final String TESSDATA_PATH = "./tessdata";
	
	private static final String LANGUAGE = "eng";

	private static TessBaseAPI instance;
	
	/**
	 * Creates an initialized instance of {@link TessBaseAPI}.
	 * This method should be called only once on startup.
	 * 
	 * @return Singleton instance of {@link TessBaseAPI}.
	 */
	public static TessBaseAPI getTesseract() {
		if (instance == null) {
			instance = initialize();
		}
		
		return instance;
	}
	
	/**
	 * Destroys the singleton instance of {@link TessBaseAPI}.
	 * This method should be called only once on the program finish.
	 */
	public static void destroyTesseract() {
		if (instance != null) {
			instance.End();
			instance.close();
		}
	}
	
	private static TessBaseAPI initialize() {
        final TessBaseAPI api = new TessBaseAPI();
		
        if (api.Init(TESSDATA_PATH, LANGUAGE) != 0) {
            throw new OCRException("Couldn't initialize Tesseract.");
        }
        
        return api;
	}
}
