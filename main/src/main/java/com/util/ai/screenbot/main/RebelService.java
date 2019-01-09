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

		vbBot.navigateToTopBetMiddle();
		vbBot.checkTopBet();

		Thread.sleep(1000);

		BufferedImage topBetScreenShot = vbBot.takeTopBetScreenshot();
		DiskUtils.saveBetToDisk(topBetScreenShot); // For debug purposes

		vbBot.betOnTopBet();
		// vbBot.removeTopBet();

		vbBrowser.initializeBettingBrowser();
		marathonbetInputBot.initialize(vbBrowser.getBrowserDimensions());

		Thread.sleep(10000);

		Boolean isOK = marathonbetInputBot.checkBettingSlip();

		marathonbetInputBot.neutralClick();

		Thread.sleep(500);

		BufferedImage odds = vbBrowser.takeBetInfoScreenshot();
		DiskUtils.saveBetInfoToDisk(odds);

		BufferedImage browsingStatus = vbBrowser.takeBrowsingStatusScreenshot();
		DiskUtils.saveBrowsingStatusToDisk(browsingStatus);

		if (isOK) {

			BufferedImage marathonOdds = marathonbetInputBot.takeBookmakerOddsScreenshot();
			DiskUtils.saveBookmakerOddsToDisk(marathonOdds);

			marathonbetInputBot.navigateToMaxStake();
			BufferedImage maxStake = marathonbetInputBot.takeMaxStakeScreenshot();
			DiskUtils.saveMaxStakeToDisk(maxStake);

			Thread.sleep(3000);

			marathonbetInputBot.navigateToMinStake();
			BufferedImage minStake = marathonbetInputBot.takeMinStakeScreenshot();
			DiskUtils.saveMinStakeToDisk(minStake);

			Thread.sleep(3000);

			BufferedImage balance = marathonbetInputBot.takeBalanceScreenshot();
			DiskUtils.saveBalanceToDisk(balance);

			Thread.sleep(1000);

			marathonbetInputBot.clickRemoveAll();

			Thread.sleep(1000);

			vbBrowser.clickCancel();

			vbBot.initializeValueBetting();

			vbBot.removeTopBet();

		}
	}

}
