package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBBookmakerMinStakeElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBookmakerMinStakeElementParser implements VBScreenElementParser<VBBookmakerMinStakeElement> {

	private static final Pattern PATTERN_MIN_STAKE = Pattern.compile(
			"(?:[^\\d]*)(\\d+\\s*(\\.\\s*\\d)?\\s*\\d*)");
	
	@Override
	public VBBookmakerMinStakeElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_MIN_STAKE.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the MIN_STAKE pattern.", input.trim()));
		}
		
		final double stake = CustomNumberFormat.parseDouble(matcher.group(1));
		return new VBBookmakerMinStakeElement(stake);
	}

}
