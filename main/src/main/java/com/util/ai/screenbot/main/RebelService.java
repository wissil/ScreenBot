package com.util.ai.screenbot.main;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.util.ai.screenbot.input.logic.marathonbet.MarathonbetInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.input.utils.DiskUtils;

public class RebelService {

	private static final Logger log = LoggerFactory.getLogger(RebelService.class);

	@Inject
	private VBMainInputBot vbBot;

	@Inject
	private VBBrowserInputBot vbBrowser;

	@Inject
	private MarathonbetInputBot marathonbetInputBot;

	public void run() throws InterruptedException {
		log.info("Service started successfully!");

		vbBot.initializeValueBetting();

		log.info("upper");
		vbBot.navigateToTopBetUpperLeft();

		Thread.sleep(3000);

		log.info("lower");
		vbBot.navigateToTopBetLowerLeft();

		Thread.sleep(3000);

		vbBot.navigateToTopBetMiddle();
		vbBot.checkTopBet();

		Thread.sleep(3000);

		BufferedImage topBetScreenShot = vbBot.takeTopBetScreenshot();
		DiskUtils.saveBetToDisk(topBetScreenShot); // For debug purposes

		Thread.sleep(3000);

		vbBot.betOnTopBet();
		// vbBot.removeTopBet();

		vbBrowser.initializeBettingBrowser();

		Thread.sleep(1000);

		vbBrowser.navigateToOddsUpperLeftCorner();
		BufferedImage odds = vbBrowser.takeOddsScreenshot();
		DiskUtils.saveOddsToDisk(odds);

		Thread.sleep(3000);

		vbBrowser.navigateToStakeUpperLeftCorner();
		BufferedImage stake = vbBrowser.takeStakeScreenshot();
		DiskUtils.saveStakeToDisk(stake);

		Thread.sleep(3000);

		vbBrowser.navigateToValueUpperLeftCorner();
		BufferedImage value = vbBrowser.takeValueScreenshot();
		DiskUtils.saveValueToDisk(value);

		vbBrowser.navigateToBrowsingStatus();
		BufferedImage browsingStatus = vbBrowser.takeBrowsingStatusScreenshot();
		DiskUtils.saveBrowsingStatusToDisk(browsingStatus);

		Thread.sleep(3000);

		marathonbetInputBot.initialize(vbBrowser.getBrowserDimensions());
		marathonbetInputBot.clickBettingSlip();

		Thread.sleep(3000);

		Boolean isOk = marathonbetInputBot.checkBettingSlip();

		if (isOk) {

			marathonbetInputBot.navigateToRemoveAllButton();

			Thread.sleep(3000);

			marathonbetInputBot.navigateToBetButton();
		}
	}

}
