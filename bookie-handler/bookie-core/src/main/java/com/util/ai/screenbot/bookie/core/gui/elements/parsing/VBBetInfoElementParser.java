package com.util.ai.screenbot.bookie.core.gui.elements.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.bookie.core.gui.elements.VBBetInfoElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBetInfoElementParser 
	implements VBScreenElementParser<VBBetInfoElement> {
		
	private static final Pattern PATTERN_BET_INFO = Pattern.compile(
			"(\\d+(?:\\.(?:\\s+)*\\d)?\\d*)"
			+ "\\s*[\\r\\n]+"
			+ "(\\d+(?:\\.(?:\\s+)*\\d)?\\d*)"
			+ "\\s*[\\r\\n]+"
			+ "(\\d+(?:\\.(?:\\s+)*\\d)?\\d*)"
			+ "\\s*[\\r\\n]*"); 

	@Override
	public VBBetInfoElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_BET_INFO.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the BET_INFO pattern.", input.trim()));
		}
		
		final double odds = CustomNumberFormat.parseDouble(matcher.group(1));
		final double stake = CustomNumberFormat.parseDouble(matcher.group(2));
		final double value = CustomNumberFormat.parseDouble(matcher.group(3));
		
		return new VBBetInfoElement(odds, stake, value);
	}

}
