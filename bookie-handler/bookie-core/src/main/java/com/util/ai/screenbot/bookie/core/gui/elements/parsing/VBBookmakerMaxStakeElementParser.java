package com.util.ai.screenbot.bookie.core.gui.elements.parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.ai.screenbot.bookie.core.gui.elements.VBBookmakerMaxStakeElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;
import com.util.ai.screenbot.support.numbers.CustomNumberFormat;

public class VBBookmakerMaxStakeElementParser implements VBScreenElementParser<VBBookmakerMaxStakeElement> {

	private static final Pattern PATTERN_MAX_STAKE = Pattern.compile(
			"(?:[^\\d]*)(\\d+\\s*(\\.\\s*\\d)?\\s*\\d*)");
	
	@Override
	public VBBookmakerMaxStakeElement parse(String input) throws ScreenElementParseException {
		final Matcher matcher = PATTERN_MAX_STAKE.matcher(input.trim());
		if (!matcher.matches()) {
			throw new ScreenElementParseException(
					String.format("Input %s doesn't correspond to the MAX_STAKE pattern.", input.trim()));
		}
		
		final double stake = CustomNumberFormat.parseDouble(matcher.group(1));
		return new VBBookmakerMaxStakeElement(stake);
	}

}
