package com.util.ai.screenbot.output.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.util.ai.screenbot.output.ocr.exceptions.OCRException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {
	
	private final Tesseract tesseract;
	
	public OCR(Tesseract tesseract) {
		this.tesseract = Objects.requireNonNull(tesseract);
	}

	public String doOcr(String imagePath, OcrReadMode mode) throws IOException {	
		final BufferedImage image = ImageIO.read(new File(imagePath));
		return doOcr(image, mode);
	}
	
	public String doOcr(BufferedImage image, OcrReadMode mode) {	
		try {
			tesseract.setLanguage(mode.getLanguage());
			return tesseract.doOCR(image);
		} catch (TesseractException e) {
			throw new OCRException("OCR failed.", e);
		}
	}

}
