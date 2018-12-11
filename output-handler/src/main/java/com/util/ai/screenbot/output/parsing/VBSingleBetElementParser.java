package com.util.ai.screenbot.output.parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.util.ai.screenbot.output.elements.VBSingleBetElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public class VBSingleBetElementParser implements VBScreenElementParser<VBSingleBetElement> {

	private static final String ANY_WHITESPACE = "\\s+";
		
	/*
	 * value, stake, [starts in, ...], [participants, ...], type,
	 * coef, @, bookie
	 */
	private static final int MIN_ELEMENTS = 8;
	
	@Override
	public VBSingleBetElement parse(String input) throws ScreenElementParseException {
		final String[] elements = input.split(ANY_WHITESPACE);
		final int lastIndex = elements.length - 1;
		
		if (elements.length < MIN_ELEMENTS) {
			throw new ScreenElementParseException(
					String.format("Illegal number of elements: %d in %s.", 
							elements.length, Arrays.toString(elements)));
		}
		
		// first element: value
		String value = elements[0];
		if (!value.contains("%")) {
			throw new ScreenElementParseException(
					String.format("Value element %s didn't contain percentage symbol [%].", value));
		}
		
		// remove the % symbol
		value = value.substring(0, value.length()-1);
		
		// second element: stake
		final String stake = elements[1];
		
		if (!elements[lastIndex-1].equals("@")) {
			throw new ScreenElementParseException("The element before last should be a symbol: @.");
		}
		
		// last element: bookie
		final String bookie = elements[lastIndex];
		final String odds = elements[lastIndex-2];
		final String outcome = elements[lastIndex-3];
		
		final List<String> participantElements = new ArrayList<>();
		
		for (int i=2, n=lastIndex-3; i<n; i++) {
			final String current = elements[i];
			
			// ignore time labels
			if (current.contains("hour") || current.contains("min")) {
				continue;
			}
			
			// participant element
			participantElements.add(current);
		}
		
		final String participants = String.join(" ", participantElements);
		
		return new VBSingleBetElement(value, stake, participants, outcome, odds, bookie);
	}

}
