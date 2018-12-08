package com.util.ai.screenbot.support.testing.time;

import java.util.concurrent.TimeUnit;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.rules.TestRule;
import org.junit.runner.Description;


public class JUnitStopwatch extends Stopwatch implements TestRule {

	@Override
	protected void succeeded(long nanos, Description description) {
		if (!isAnnotated(description)) return;
		logInfo(description, "succeeded", nanos);
	}

	@Override
	protected void failed(long nanos, Throwable e, Description description) {
		if (!isAnnotated(description)) return;
		logInfo(description, "failed", nanos);
	}

	@Override
	protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
		if (!isAnnotated(description)) return;
		logInfo(description, "skipped", nanos);
	}
	
	private static boolean isAnnotated(Description description) {
		return description.getAnnotation(TimedExecution.class) != null;
	}
	
	private static void logInfo(Description description, String status, long nanos) {
		final String testName = description.getMethodName();
		
		System.out.println(String.format("Test %s: %s. Execution time: %d microseconds.%s",
				testName, status, TimeUnit.NANOSECONDS.toMicros(nanos), System.lineSeparator()));
	}
}
