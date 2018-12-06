package com.util.ai.screenbot.output.parsing;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.parsing.exceptions.ScreenElementParseException;

public interface VBScreenElementParser<E extends VBScreenElement> {

	E parse(String input) throws ScreenElementParseException;
}
