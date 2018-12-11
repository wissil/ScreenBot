package com.util.ai.screenbot.output.interpreters;


import java.util.Objects;

import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.ocr.OCR;
import com.util.ai.screenbot.output.parsing.VBScreenElementParser;

public abstract class AbstractVBElementInterpreter<E extends VBScreenElement, P extends VBScreenElementParser<E>> 
	implements VBElementInterpreter<E> {
	
	protected final OCR ocr;
	
	protected final P parser;
		
	public AbstractVBElementInterpreter(OCR ocr, P parser) {
		this.ocr = Objects.requireNonNull(ocr);
		this.parser = Objects.requireNonNull(parser);
	}
}
