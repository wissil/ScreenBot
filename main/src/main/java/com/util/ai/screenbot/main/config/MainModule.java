package com.util.ai.screenbot.main.config;

import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.util.ai.screenbot.input.logic.VBInputBot;
import com.util.ai.screenbot.main.automata.VBStateMachine;
import com.util.ai.screenbot.main.automata.VBStateMachineMock;
import com.util.ai.screenbot.main.handlers.input.InputHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandlerImpl;
import com.util.ai.screenbot.main.handlers.output.OutputHandler;
import com.util.ai.screenbot.main.handlers.output.OutputHandlerImpl;
import com.util.ai.screenbot.output.interpreters.VBOddsInputInterpreter;
import com.util.ai.screenbot.output.interpreters.VBPlaceBetInterpreter;
import com.util.ai.screenbot.output.interpreters.VBSingleBetInterpreter;
import com.util.ai.screenbot.output.ocr.TesseractAPI;

public class MainModule extends AbstractModule {
	
    private static final String TESSDATA_PATH = "../output-handler/tessdata";

    private static final String LANGUAGE = "eng";
    
    @Inject
	@Provides
	@Singleton
    VBStateMachine stateMachine(InputHandler in, OutputHandler out) {
    		return new VBStateMachineMock(in, out);
    }
	
	@Provides
	@Singleton
	TessBaseAPI tesseract() {
		return TesseractAPI.getTesseract(TESSDATA_PATH, LANGUAGE);
	}

	@Inject
	@Provides
	@Singleton
	InputHandler inputHandler(VBInputBot inputBot) {
		return new InputHandlerImpl(inputBot);
	}
	
	@Inject
	@Provides
	@Singleton
	OutputHandler outputHandler(
			VBSingleBetInterpreter singleBetInterpreter,
			VBOddsInputInterpreter oddsInputInterpreter,
			VBPlaceBetInterpreter placeBetInterpreter,
			TessBaseAPI tesseract) {
		
		return new OutputHandlerImpl(
				singleBetInterpreter, 
				oddsInputInterpreter, 
				placeBetInterpreter, tesseract);
	}
}
