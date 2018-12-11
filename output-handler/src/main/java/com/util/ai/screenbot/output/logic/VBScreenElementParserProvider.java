package com.util.ai.screenbot.output.logic;

import java.util.Objects;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.parsing.VBOddsInputElementParser;
import com.util.ai.screenbot.output.parsing.VBPlaceBetElementParser;
import com.util.ai.screenbot.output.parsing.VBScreenElementParser;
import com.util.ai.screenbot.output.parsing.VBSingleBetElementParser;
import com.util.ai.screenbot.support.constants.VBScreenElementType;

public class VBScreenElementParserProvider {
	
	private final VBOddsInputElementParser oddsInputParser;
	
	private final VBPlaceBetElementParser placeBetParser;
	
	private final VBSingleBetElementParser singleBetParser;
	
	public VBScreenElementParserProvider(
			VBOddsInputElementParser oddsInputParser,
			VBPlaceBetElementParser placeBetParser,
			VBSingleBetElementParser singleBetParser) {
		this.oddsInputParser = Objects.requireNonNull(oddsInputParser);
		this.placeBetParser = Objects.requireNonNull(placeBetParser);
		this.singleBetParser = Objects.requireNonNull(singleBetParser);
	}

	public VBScreenElementParser<? extends VBScreenElement> provideParser(VBScreenElementType type) {
		switch (type) {
		case VB_ODDS_INPUT:
			return oddsInputParser;

		case VB_PLACE_BET:
			return placeBetParser;
			
		case VB_SINGLE_BET:
			return singleBetParser;
			
		default:
			throw new IllegalArgumentException(
					String.format("No corresponding parser found for type %s.", type));
		}
	}
}
