package com.util.ai.screenbot.input.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskUtils {

	private static final Logger log = LoggerFactory.getLogger(DiskUtils.class);

	private static final String LOG_DIR_ROOT = "./external/";

	public static void saveBetToDisk(BufferedImage image) {

		saveToDisk(image, "singleBets");
	}

	public static void saveBetInfoToDisk(BufferedImage image) {

		saveToDisk(image, "betInfo");
	}

	public static void saveBrowsingStatusToDisk(BufferedImage image) {

		saveToDisk(image, "browsingStatus");
	}

	public static void saveBookmakerOddsToDisk(BufferedImage image) {

		saveToDisk(image, "bookmakerOdds");
	}

	public static void saveBalanceToDisk(BufferedImage image) {

		saveToDisk(image, "balance");
	}

	public static void saveMaxStakeToDisk(BufferedImage image) {

		saveToDisk(image, "maxStake");
	}

	public static void saveMinStakeToDisk(BufferedImage image) {

		saveToDisk(image, "minStake");
	}

	public static void logBetToFile(String betLog) {

		BufferedWriter bw = null;
		FileWriter fw = null;

		File outputDir = new File(LOG_DIR_ROOT + "betLog/");
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "betLog_" + dt.format(new Date()) + ".txt";
		File betLogFile = new File(outputDir, fileName);
		if (!betLogFile.exists()) {
			try {
				betLogFile.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException("Not able to create bet log file", e);
			}
		}

		try {

			fw = new FileWriter(betLogFile, true);
			bw = new BufferedWriter(fw);
			bw.write(betLog);
			bw.newLine();

		} catch (IOException e) {

			throw new RuntimeException("Not able to log bet", e);

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				throw new RuntimeException("Not able to close file stream", ex);
			}
		}
	}

	private static void saveToDisk(BufferedImage image, String dir) {

		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		File outputDir = new File(LOG_DIR_ROOT + dir + "/");
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
