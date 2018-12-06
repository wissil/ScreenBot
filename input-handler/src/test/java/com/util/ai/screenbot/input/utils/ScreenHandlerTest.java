package com.util.ai.screenbot.input.utils;

import java.awt.Rectangle;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.input.config.InputHandlerTestBase;
import com.util.ai.screenbot.input.utils.ScreenHandler;
import com.util.ai.screenbot.support.utils.IgnoreOnPlatform;
import com.util.ai.screenbot.support.utils.OS;

public class ScreenHandlerTest extends InputHandlerTestBase {

	@Inject
	private ScreenHandler screenHandler;

	@Test
	@IgnoreOnPlatform(value = OS.MAC)
	public void testGetCurrentWindowsName() {
		String windowName = screenHandler.getCurrentWindowsName();

		Assert.assertNotNull(windowName);
		Assert.assertTrue(windowName.startsWith("screenBot"));

		System.out.println("Window name: " + windowName);
	}

	@Test
	@IgnoreOnPlatform(value = OS.MAC)
	public void testGetRect() {
		Rectangle rect = screenHandler.getRect();

		Assert.assertNotNull(rect);
		System.out.println("Window rect: " + rect.toString());
	}

	@Test
	@IgnoreOnPlatform(value = OS.WINDOWS)
	public void macTest() throws ScriptException {
		final String script= "tell application \"System Events\"\n" +
				"\tname of application processes whose frontmost is true\n" +
				"end";
		ScriptEngine appleScript = new ScriptEngineManager().getEngineByName("AppleScriptEngine");
		String result = appleScript.eval(script).toString();
		System.out.println(result);
	}
}
