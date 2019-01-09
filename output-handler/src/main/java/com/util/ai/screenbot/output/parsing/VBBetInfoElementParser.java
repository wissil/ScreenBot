package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBBetInfoElementParser 
	implements VBScreenElementParser<VBBetInfoElement> {
		
	private static final Pattern PATTERN_BET_INFO = Pattern.compile(
			"(\\d+(?:\\.\\d)?\\d*)"
			+ "\\s*[\\r\\n]+"
			+ "(\\d+(?:\\.\\d)?\\d*)"
			+ "\\s*[\\r\\n]+"
			+ "(\\d+(?:\\.\\d)?\\d*)"
			+ "\\s*[\\r\\n]*"); 

	@Override
	public VBBetInfoElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_BET_INFO.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the BET_INFO pattern.", input.trim()));
		}
		
		final String odds = matcher.group(1);
		final String stake = matcher.group(2);
		final String value = matcher.group(3);
		
		return new VBBetInfoElement(odds, stake, value);
	}

}
