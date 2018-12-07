package com.util.ai.screenbot.support.testing;

import java.util.Arrays;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class IgnoreOnPlatformRule implements TestRule {

	@Override
	public Statement apply(Statement statement, Description description) {
		final IgnoreOnPlatform annotation = 
				description.getAnnotation(IgnoreOnPlatform.class);

		if (annotation == null) {
			return statement;
		}

		if (isCurrentPlatformIgnored(annotation, OS.MAC) || 
				isCurrentPlatformIgnored(annotation, OS.WINDOWS)) {
			return emptyStatement();
		}

		return statement;
	}
	
	private static boolean isCurrentPlatformIgnored(IgnoreOnPlatform annotation, OS platform) {
		return isCurrentPlatform(platform) && isPlatformIgnored(annotation, platform);
	}
	
	private static boolean isPlatformIgnored(IgnoreOnPlatform annotation, OS platform) {
		return Arrays.asList(annotation.value()).contains(platform);
	}

	private static boolean isCurrentPlatform(OS platform) {
		if (platform.equals(OS.MAC)) return isCurrentPlatformMac();
		if (platform.equals(OS.WINDOWS)) return isCurrentPlatformWin();
		return false;
	}
	
	private static boolean isCurrentPlatformMac() {
		return System.getProperty("os.name").trim().toLowerCase().startsWith("mac");
	}
	
	private static boolean isCurrentPlatformWin() {
		return System.getProperty("os.name").trim().toLowerCase().startsWith("windows");
	}
	
	private static Statement emptyStatement() {
		return new Statement() {
			
			@Override
			public void evaluate() {}
		};
	}
}
