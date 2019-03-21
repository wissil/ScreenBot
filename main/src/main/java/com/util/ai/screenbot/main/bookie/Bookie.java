package com.util.ai.screenbot.main.bookie;

import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.bet365Handler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.expertHandler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.marathonBetHandler;
import static com.util.ai.screenbot.main.bookie.handlers.BookieHandlerProvider.williamHillHandler;
import static com.util.ai.screenbot.support.strings.StringComparator.consideredEqual;

import java.util.Arrays;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.main.bookie.handlers.BookieHandler;
import com.util.ai.screenbot.output.elements.gui.bookie.BookieGraphics;
import com.util.ai.screenbot.output.elements.ocr.conf.marathon.MarathonGraphics;
import com.util.ai.screenbot.output.elements.ocr.conf.williamhill.WilliamHillGraphics;

public enum Bookie {

    MARATHON_BET("Marathonbet", marathonBetHandler(), true) {

        @Override
        public BookieGraphics getGraphics() {
            return new MarathonGraphics();
        }
    },

    WILLIAM_HILL("WilliamHill", williamHillHandler(), true) {
    	
    		@Override
    		public BookieGraphics getGraphics() {
    			return new WilliamHillGraphics();
    		}
    },

    BET_365("Bet365", bet365Handler(), true),

    EXPERT("Expert", expertHandler(), false);

    final String bookieName;

    final BookieHandler handler;

    final boolean rebelReady;

    Bookie(String bookieName, BookieHandler handler, boolean rebelReady) {
        this.bookieName = Objects.requireNonNull(bookieName);
        this.handler = Objects.requireNonNull(handler);
        this.rebelReady = rebelReady;
    }

    public String getBookieName() {
        return bookieName;
    }

    public BookieHandler getHandler() {
        return handler;
    }

    public BookieGraphics getGraphics() {
        // implement for each bookie
        return null;
    }

    public void waitForBettingBrowserToLoad() throws BettingBrowserTimeoutException {
        this.handler.waitForBettingBrowserToLoad();
    }

    public boolean isRebelReady() {
        return this.rebelReady;
    }

    public static Bookie fromString(String s) throws UnknownBookieException {
        return Arrays.stream(Bookie.values()).filter(b -> consideredEqual(b.getBookieName(), s)).findFirst()
                .orElseThrow(() -> new UnknownBookieException(String.format("No bookie similar to '%s' was found.", s)));
    }

    @Override
    public String toString() {
        return bookieName;
    }
}
