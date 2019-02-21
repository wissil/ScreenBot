package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBookmakerOddsElementParser 
	implements VBScreenElementParser<VBBookmakerOddsElement> {
	
	private static final Pattern PATTERN_ODDS = Pattern.compile(
			"(?:[^\\d]*)(\\d+\\s*(\\.\\s*\\d)?\\s*\\d*)");

	@Override
	public VBBookmakerOddsElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_ODDS.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the BOOKMAKER_ODDS pattern.", input.trim()));
		}
		
		final String oddsText = matcher.group(1);
		
		try {
			final double odds = CustomNumberFormat.parseDouble(oddsText);
			return new VBBookmakerOddsElement(odds);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input %s to bookie odds.", oddsText));
		}
	}

}
