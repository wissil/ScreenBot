package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBSingleBetElementParser implements VBScreenElementParser<VBSingleBetElement> {

	private static final Pattern PATTERN_SINGLE_BET = Pattern.compile("([0-9]+\\s*(?:\\.|,)*\\s*[0-9]+)\\s*%"
			+ "[ \\t]*" + "([0-9]+)" + "[ \\t]+" + "(.+[ \\t]*(?:hour|hours|min|sec)[ \\t]*(?:[.]+[ \\t]*min)?)"
			+ "[ \\t]+" + "([a-zA-Z0-9].*)" + "[ \\t]+" + "((?:[a-zA-Z]+[^\\s\\\\]*)|(?:[0-9]+)|(?:\\(.+\\)))"
			+ "(?:[ \\t]+[a-zA-Z0-9]*[ \\t]*)" + "([0-9]+(?:(?:\\.|,)[0-9]+)?)" + "[ \\t]*" + "@" + "[ \\t]*" + "(.+)");

	@Override
	public VBSingleBetElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_SINGLE_BET.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the SINGLE_BET pattern.", input.trim()));
		}

		try {
			final double value = CustomNumberFormat.parseDouble(matcher.group(1));
			final double stake = CustomNumberFormat.parseDouble(matcher.group(2));
			final String participants = matcher.group(4);
			final String outcome = matcher.group(5);
			final double odds = CustomNumberFormat.parseDouble(matcher.group(6));
			final String bookie = matcher.group(7);

			return new VBSingleBetElement(value, stake, participants, outcome, odds, bookie);
		} catch (Exception e) {
			throw new ScreenElementParseException("Error occurred while parsing to the SINGLE_BET pattern.", e);
		}
	}

}
