package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBBetInfoElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
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
		
		final String oddsText = matcher.group(1);
		final String stakeText = matcher.group(2);
		final String valueText = matcher.group(3);
		
		double odds = 0.;
		double stake = 0.;
		double value = 0.;
		
		try {
			odds = CustomNumberFormat.parseDouble(oddsText);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input %s to VB odds.", oddsText));
		}
		
		try {
			stake = CustomNumberFormat.parseDouble(stakeText);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input %s to VB stake.", stakeText));
		}
		
		try {
			value = CustomNumberFormat.parseDouble(valueText);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input %s to VB value.", valueText));
		}
		
		return new VBBetInfoElement(odds, stake, value);
	}

}
