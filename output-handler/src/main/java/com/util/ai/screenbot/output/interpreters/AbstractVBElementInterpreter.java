package com.util.ai.screenbot.output.interpreters;


import com.util.ai.screenbot.output.elements.VBScreenElement;
import com.util.ai.screenbot.output.ocr.OCR;

public abstract class AbstractVBElementInterpreter<E extends VBScreenElement> 
	implements VBElementInterpreter<E> {
	
	protected final OCR ocr;
		
	public AbstractVBElementInterpreter() {
		this.ocr = new OCR();
	}
}
