package com.util.ai.screenbot.input.handlers.screen;

import java.awt.Rectangle;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import com.util.ai.screenbot.input.handlers.util.ActiveWindow;

public class MacScreenHandler extends ScreenHandler {
	
	private static final String SCRIPT_ACTIVE_WINDOW = 
			"global frontApp, frontAppName\n" + 
			"\n" + 
			"set windowTitle to \"\"\n" + 
			"tell application \"System Events\"\n" + 
			"	set frontApp to first application process whose frontmost is true\n" + 
			"	set frontAppName to name of frontApp\n" + 
			"end tell\n" + 
			"\n" + 
			"return frontAppName";
	
	private static final String SCRIPT_WINDOW_BOUNDS = 
			"tell application \"System Events\" to tell application process \"%s\"\n" + 
			"	get {size, position} of window 0\n" + 
			"end tell";
	
	 private static final Pattern PATTERN_BOUNDS = Pattern 
			   .compile("\\[\\[(\\d+), (\\d+)\\], \\[(\\d+), (\\d+)\\]\\]");
	
	private final ScriptEngine engine;
	
	public MacScreenHandler(ScriptEngine engine) {
		this.engine = Objects.requireNonNull(engine);
	}

	@Override
	public ActiveWindow getActiveWindow() throws Exception {
		final String name = getCurrentWindowName();
		final String boundsTextual = getBoundsForWindow(name);
		
		final Matcher matcher = PATTERN_BOUNDS.matcher(boundsTextual);
		matcher.matches();
		
		final int width = Integer.parseInt(matcher.group(1));
		final int height = Integer.parseInt(matcher.group(2));
		final int x = Integer.parseInt(matcher.group(3));
		final int y = Integer.parseInt(matcher.group(4));

		return new ActiveWindow(name, new Rectangle(x, y, width, height));
	}

	private String getCurrentWindowName() throws ScriptException {
		return engine.eval(SCRIPT_ACTIVE_WINDOW).toString().trim();
	}
	
	private String getBoundsForWindow(String windowName) throws ScriptException, InterruptedException {
		return engine.eval(String.format(SCRIPT_WINDOW_BOUNDS, windowName)).toString().trim();
	}
}
