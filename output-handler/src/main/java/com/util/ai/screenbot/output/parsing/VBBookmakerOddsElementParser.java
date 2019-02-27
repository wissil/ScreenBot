package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBBookmakerOddsElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.strings.StringSanitizer;

import static com.util.ai.screenbot.support.numbers.CustomNumberFormat.parseDouble;
import static com.util.ai.screenbot.support.numbers.CustomNumberFormat.parseInt;
import static com.util.ai.screenbot.support.numbers.NumberFormatConverter.convertFromFractionToDecimal;

public class VBBookmakerOddsElementParser 
	implements VBScreenElementParser<VBBookmakerOddsElement> {
	
	private static final Pattern PATTERN_ODDS_DECIMAL = Pattern.compile(
			"(?:[^\\d]*)(\\d+\\s*((?:\\.|,)\\s*\\d)?\\s*\\d*)");
	
	private static final Pattern PATTERN_ODDS_FRACTION = Pattern.compile(
			"(\\s*\\d*\\s*\\/\\s*\\d*\\s*)");

	@Override
	public VBBookmakerOddsElement parse(String input) throws ScreenElementParseException {
		input = StringSanitizer.forString(input).fromAllWhitespaces().fromAllNonNumeric().sanitize().trim();
		final Matcher matcherDecimal = PATTERN_ODDS_DECIMAL.matcher(input);
		final Matcher matcherFraction = PATTERN_ODDS_FRACTION.matcher(input);
		
		try {
			if (matcherFraction.matches()) {
				final String[] oddsText = matcherFraction.group(1).split("/");
				final double odds = convertFromFractionToDecimal(parseInt(oddsText[0]), parseInt(oddsText[1]));
				return new VBBookmakerOddsElement(odds);
			}
			
			if (matcherDecimal.matches()) {
				final String oddsText = matcherDecimal.group(1);
				final double odds = parseDouble(oddsText);
				return new VBBookmakerOddsElement(odds);
			}
		} catch (NumberFormatException e) {
			throw new ScreenElementParseException("Bookmaker odds elements couldn't be parsed.", e);
		}
		
		throw new ScreenElementParseException(
				String.format("Input %s doesn't correspond to the BOOKMAKER_ODDS pattern.", input.trim()));
	}

}
