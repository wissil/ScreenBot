package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBSingleBetElementParser implements VBScreenElementParser<VBSingleBetElement> {

	private static final Pattern PATTERN_SINGLE_BET = Pattern.compile(
			".+" + 
			"[ \\t]+" + 
			"(?:.*(?:hour|hours|min|mins|sec)[ \\t]*(?:[.]+[ \\t]*min)?)" + 
			"[ \\t]+" + 
			"([a-zA-Z0-9].*)" + 
			"[ \\t]+" + 
			"(?:(?:[a-zA-Z]+\\s*[^\\s\\\\]*)|(?:[0-9]+)|(?:\\(.+\\)))" + 
			"(?:[ \\t]+[a-zA-Z0-9]*[ \\t]*)" + 
			"(?:[0-9]+(?:(?:\\.|,)[0-9]+)?)" + 
			"[ \\t]*" + 
			"@" + 
			"[ \\t]*" + 
			"([a-zA-Z0-9\\s*]+)" +
			".*");

	@Override
	public VBSingleBetElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_SINGLE_BET.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the SINGLE_BET pattern.", input.trim()));
		}
		
		try {
			final String participants = matcher.group(1).trim();
			final String bookie = matcher.group(2).trim();

			return new VBSingleBetElement(participants, bookie);
		} catch (Exception e) {
			throw new ScreenElementParseException("Error occurred while parsing to the SINGLE_BET pattern.", e);
		}
	}

}
