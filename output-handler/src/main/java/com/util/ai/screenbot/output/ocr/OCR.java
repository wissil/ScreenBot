package com.util.ai.screenbot.output.ocr;

import java.io.IOException;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class OCR {

	public String doOcr(TessBaseAPI api, String imagePath) throws IOException {
        // open input image with leptonica library		
        final PIX image = lept.pixRead(imagePath);
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
