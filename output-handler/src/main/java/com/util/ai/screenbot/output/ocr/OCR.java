package com.util.ai.screenbot.output.ocr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class OCR {

	public String doOcr(TessBaseAPI api, String imagePath) throws IOException {	
        final PIX image = readPixFromPath(imagePath);
		return doOcr(image, api);
	}
	
	public String doOcr(TessBaseAPI api, BufferedImage bImage) throws IOException {
		final PIX image = convertToPixFromBufferedImage(bImage);
		return doOcr(image, api);
	}
	
	private PIX convertToPixFromBufferedImage(BufferedImage bImage) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(bImage, "png", os);
		
        final byte[] bytes = os.toByteArray();
        return lept.pixReadMemPng(bytes, bytes.length);
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
