package com.util.ai.screenbot.main.bookie;

import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.bet365Handler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.marathonBetHandler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.williamHillHandler;
import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

import java.util.Arrays;
import java.util.Objects;

import com.util.ai.screenbot.main.bookie.handlers.BookieHandler;
import com.util.ai.screenbot.output.elements.gui.bookie.BookieGraphics;
import com.util.ai.screenbot.output.elements.ocr.conf.marathon.MarathonGraphics;


public enum Bookie {

	MARATHON_BET("Marathonbet", marathonBetHandler(), new MarathonGraphics()),

	WILLIAM_HILL("WilliamHill", williamHillHandler(), null),

	BET_365("Bet365", bet365Handler(), null);

	final String bookieName;

	final BookieHandler handler;
	
	final BookieGraphics graphics;

	Bookie(String bookieName, BookieHandler handler, BookieGraphics graphics) {
		this.bookieName = Objects.requireNonNull(bookieName);
		this.handler = Objects.requireNonNull(handler);
		this.graphics = Objects.requireNonNull(graphics);
	}

	public String getBookieName() {
		return bookieName;
	}

	public BookieHandler getHandler() {
		return handler;
	}
	
	public BookieGraphics getGraphics() {
		return graphics;
	}

	public static Bookie fromString(String s) throws UnknownBookieException {
		return Arrays.stream(Bookie.values()).filter(b -> consideredEqual(b.getBookieName(), s)).findFirst()
				.orElseThrow(
						() -> new UnknownBookieException(String.format("No bookie similar to '%s' was found.", s)));
	}
}
