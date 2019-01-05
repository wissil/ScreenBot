package com.util.ai.screenbot.main.bookie;

import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.bet365Handler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.marathonBetHandler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.williamHillHandler;
import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

import java.util.Arrays;

import com.util.ai.screenbot.main.bookie.handlers.BookieHandler;

public enum Bookie {

	MARATHON_BET("Marathonbet", marathonBetHandler()),

	WILLIAM_HILL("WilliamHill", williamHillHandler()),

	BET_365("Bet365", bet365Handler());

	final String bookieName;

	final BookieHandler handler;

	Bookie(String bookieName, BookieHandler handler) {
		this.bookieName = bookieName;
		this.handler = handler;
	}

	public String getBookieName() {
		return bookieName;
	}

	public BookieHandler getHandler() {
		return handler;
	}

	public static Bookie fromString(String s) throws UnknownBookieException {
		return Arrays.stream(Bookie.values()).filter(b -> consideredEqual(b.getBookieName(), s)).findFirst()
				.orElseThrow(
						() -> new UnknownBookieException(String.format("No bookie similar to '%s' was found.", s)));
	}
}
