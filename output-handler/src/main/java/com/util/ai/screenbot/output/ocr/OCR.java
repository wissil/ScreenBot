package com.util.ai.screenbot.output.ocr;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.LeptonicaFrameConverter;

public class OCR {
	
	private final Java2DFrameConverter javaConverter;
	
	private final LeptonicaFrameConverter leptonicaConverter;
	
	public OCR(Java2DFrameConverter javaConverter, LeptonicaFrameConverter leptonicaConverter) {
		this.javaConverter = Objects.requireNonNull(javaConverter);
		this.leptonicaConverter = Objects.requireNonNull(leptonicaConverter);
	}

	public String doOcr(TessBaseAPI api, String imagePath) throws IOException {	
        final PIX image = readPixFromPath(imagePath);
		return doOcr(image, api);
	}
	
	public String doOcr(TessBaseAPI api, BufferedImage bImage) throws IOException {
		final PIX image = convertToPixFromBufferedImage(bImage);
		return doOcr(image, api);
	}
	
	private PIX convertToPixFromBufferedImage(BufferedImage bImage) {
		return leptonicaConverter.convert(javaConverter.convert(bImage));
	}
	
	private static PIX readPixFromPath(String imagePath) {
        // open input image with leptonica library		
		return lept.pixRead(imagePath);
	}
	
	private static String doOcr(PIX image, TessBaseAPI api) throws IOException {
        api.SetImage(image);
        
        // perform OCR
        final BytePointer outText = api.GetUTF8Text();
        final String text = outText.getString("UTF-8");

        // destroy used object and release memory
        outText.deallocate();
        lept.pixDestroy(image);
                
        return text;
	}

}
