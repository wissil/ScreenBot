package com.util.ai.screenbot.main.automata;

import static com.util.ai.screenbot.output.interpreters.VBElementInterpreterProvider.singleBetInterpreter;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.elements.gui.general.VBSingleBetGui;

public class VBStateMachineMock extends AbstractVBStateMachine {
	
    public VBStateMachineMock(InputHandler in, OutputHandler out) {
		super(in, out);
	}

	private static final Logger log = LoggerFactory.getLogger(VBStateMachineMock.class);

    @Override
	public void cleanBet() throws InterruptedException {
		Thread.sleep(1000);
		log.info("Cleaning bet ...");
	}

    @Override
	public void logBet() throws InterruptedException {
		Thread.sleep(1000);
		log.info("Logging bet ...");
	}
    
    @Override
	public void placeBet() throws InterruptedException {
		Thread.sleep(1000);
		log.info("Placing bet ...");
		final File root = new File("../output-handler/external/singleBets/");
		
		try {
			for (File f : root.listFiles()) {
				final BufferedImage image = ImageIO.read(f);
				final VBSingleBetGui gui = new VBSingleBetGui(image);
				final VBSingleBetElement element = singleBetInterpreter().interpret(gui);
				log.info("Element: " + element);
			}
		} catch (Exception e) {
			log.error("Error!", e);
		}

	}

    @Override
	public boolean parseBet() throws InterruptedException {
		Thread.sleep(1000);
		log.info("Processing bet ...");
		return Math.random() > 0.5;
	}

    @Override
	public boolean idle() throws InterruptedException {
		while (true) {
			Thread.sleep(1000);
			log.info("Idling ...");
			
			if (Math.random() < 0.2) return true;
		}
	}

    @Override
	public void init() {
		log.info("Initializing ...");
	}
}
