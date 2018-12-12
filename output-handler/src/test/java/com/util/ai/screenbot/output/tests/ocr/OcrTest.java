package com.util.ai.screenbot.output.tests.ocr;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.ocr.TesseractAPI;
import com.util.ai.screenbot.output.tests.config.OutputHandlerTestBase;
import com.util.ai.screenbot.support.testing.time.TimedExecution;

public class OcrTest extends OutputHandlerTestBase {

	private static final String TESSDATA_PATH = "./tessdata";

	private static final String LANGUAGE = "eng";

	@Inject
	private OCR ocr;

	private static TessBaseAPI api;

	private static BufferedImage bImage;

	@BeforeClass
	public static void setup() throws IOException {
		// init API
		api = TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
		bImage = ImageIO.read(new File("./external/res/zelenilo.jpg"));
	}

	@Test
	@TimedExecution
	public void ocrFromImagePath_SingleFile_Test() throws IOException {
		final BufferedImage inputFile = ImageIO.read(new File("./external/res/jakoKvalitetno-kliknuto.png"));
		final BufferedImage bw = 
				new BufferedImage(inputFile.getWidth(), inputFile.getHeight(), BufferedImage.TYPE_INT_ARGB);

		ColorConvertOp op = 
				new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		BufferedImage dest = op.filter(inputFile, null);
		ImageIO.write(dest, "png", new File("./external/output/jakoKvalitetno-kliknutoBW1.png"));

		final String textual1 = ocr.doOcr(api, inputFile);

		final Color white = new Color(255, 255, 255);
		final Color black = new Color(0, 0, 0);

		for (int x = 0; x < inputFile.getWidth(); x++) {
			for (int y = 0; y < inputFile.getHeight(); y++) {
				int rgba = inputFile.getRGB(x, y);
				Color col = new Color(rgba, true);

				if (isWhite(col)) {
					inputFile.setRGB(x, y, black.getRGB());
				} else {
					inputFile.setRGB(x, y, white.getRGB());
				}
				
//				inputFile.setRGB(x, y, myCol.getRGB());

				//				col = new Color(255 - col.getRed(), 255 - col.getGreen(),
				//						255 - col.getBlue());
				//				inputFile.setRGB(x, y, col.getRGB());
			}
		}

		ImageIO.write(inputFile, "png", new File("./external/output/kliknuto8.png"));


		final String textual2 = ocr.doOcr(api, inputFile);
		System.out.println(textual1);
		System.out.println(textual2);
	}

	private static boolean isWhite(Color col) {
		final int tresh = 210;
		return col.getGreen() >= tresh || col.getBlue() >= tresh || col.getRed() >= tresh;
	}
	//	
	//	@Test
	//	@TimedExecution
	//	public void ocrFromBufferedImage_SingleFile_Test() throws IOException {
	//		final String textual = ocr.doOcr(api, bImage);
	//		System.out.println(textual);
	//	}
	//	
	//	@Test
	//	@Ignore
	//	public void interpretMultipleImagesTest() throws IOException {
	//		String imagePath = "./external/res/t1.png";
	//		String textual = ocr.doOcr(api, imagePath);
	//		System.out.println(textual);
	//		
	//		imagePath = "./external/res/test2.jpg";
	//		textual = ocr.doOcr(api, imagePath);
	//		System.out.println(textual);
	//	}

	@AfterClass
	public static void after() {
		// close API
		TesseractAPI.destroyTesseract(api);
	}
}
