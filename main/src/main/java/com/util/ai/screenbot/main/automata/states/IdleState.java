package com.util.ai.screenbot.main.automata.states;

import static com.util.ai.screenbot.input.constants.VBGuiConstants.SINGLE_BET_HEADER;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.value.betting.VBColors;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.utils.SikuliUtils;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.support.colors.ColorComparator;
import com.util.ai.screenbot.support.colors.ColorDeterminator;
import com.util.ai.screenbot.support.email.EmailSender;

public class IdleState extends VBState {

	private static final Logger log = LoggerFactory.getLogger(IdleState.class);

	public IdleState(InputHandler in, OutputHandler out, EmailSender email) {
		super(in, out, email);
	}

	@Override
	void onEnter() {
		log.debug("Entered IDLE state!");
	}

	@Override
	void onExit() {
		log.debug("Exiting IDLE state...");
	}

	@Override
	void execute() throws InterruptedException, FatalVBException {

		while (true) {

			/*
			 * This will run indefinitely, or as long as the Blank element doesn't vanish
			 * below the Single Bet header.
			 */
			try {
				final BufferedImage placeholder = SikuliUtils.getImageBelow(SINGLE_BET_HEADER, 20);
				final Color currentColor = ColorDeterminator.determine(placeholder);
				
				if (ColorComparator.areEqualColors(currentColor, VBColors.SINGLE_BET_PRESENT_COLOR, 0.15)) {
					new ParseBetState(in, out, email).process();
				}			
			} catch (GuiElementNotFoundException e) {
				throw new FatalVBException("Gui element not found.", e);
			}			
		}
	}

}
