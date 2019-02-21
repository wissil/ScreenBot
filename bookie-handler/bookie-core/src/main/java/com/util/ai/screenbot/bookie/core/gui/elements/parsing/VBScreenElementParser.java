package com.util.ai.screenbot.bookie.core.gui.elements.parsing;

import com.util.ai.screenbot.bookie.core.gui.elements.VBScreenElement;
import com.util.ai.screenbot.bookie.core.gui.elements.parsing.exceptions.ScreenElementParseException;

public interface VBScreenElementParser<E extends VBScreenElement> {

	E parse(String input) throws ScreenElementParseException;
}
