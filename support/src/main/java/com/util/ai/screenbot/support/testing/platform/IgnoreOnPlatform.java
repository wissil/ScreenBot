package com.util.ai.screenbot.support.testing.platform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.util.ai.screenbot.support.platform.Platform;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IgnoreOnPlatform {
	
    Platform[] value();
}
