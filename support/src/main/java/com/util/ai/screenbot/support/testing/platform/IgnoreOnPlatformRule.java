package com.util.ai.screenbot.support.testing.platform;

import java.util.Arrays;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.util.ai.screenbot.support.platform.Platform;
import com.util.ai.screenbot.support.platform.PlatformResolver;

public class IgnoreOnPlatformRule implements TestRule {

	@Override
	public Statement apply(Statement statement, Description description) {
		final IgnoreOnPlatform annotation = 
				description.getAnnotation(IgnoreOnPlatform.class);

		if (annotation == null) {
			return statement;
		}
		
		final Platform platform = PlatformResolver.resolveCurrentPlatform();
		
		if (isPlatformIgnored(annotation, platform)) {
			return emptyStatement();
		}

		return statement;
	}
	
	private static boolean isPlatformIgnored(IgnoreOnPlatform annotation, Platform platform) {
		return Arrays.asList(annotation.value()).contains(platform);
	}
	
	private static Statement emptyStatement() {
		return new Statement() {
			
			@Override
			public void evaluate() {}
		};
	}
}
