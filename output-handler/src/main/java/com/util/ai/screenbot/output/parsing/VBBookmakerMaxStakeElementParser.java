package com.util.ai.screenbot.output.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.output.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;
import com.util.ai.screenbot.support.strings.StringSanitizer;

public class VBBookmakerMaxStakeElementParser implements VBScreenElementParser<VBBookmakerMaxStakeElement> {

	private static final Pattern PATTERN_MAX_STAKE = Pattern.compile(
			"(?:[^\\d]*)(\\d+\\s*(\\.\\s*\\d)?\\s*\\d*)");
	
	@Override
	public VBBookmakerMaxStakeElement parse(String input) throws ScreenElementParseException {
		input = StringSanitizer.forString(input).fromAllWhitespaces().fromAllNonNumeric().sanitize();
		final Matcher matcher = PATTERN_MAX_STAKE.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the MAX_STAKE pattern.", input.trim()));
		}
		
		final String maxStakeText = matcher.group(1);
		
		try {
			final double odds = CustomNumberFormat.parseDouble(maxStakeText);
			return new VBBookmakerMaxStakeElement(odds);
		} catch (Exception e) {
			throw new ScreenElementParseException(
					String.format("Couldn't parse input %s to max stake.", maxStakeText));
		}
	}

}
