package com.util.ai.screenbot.support.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.util.ai.screenbot.support.email.EmailSender;
import com.util.ai.screenbot.support.image.BWImageProcessor;

public class SupportModule extends AbstractModule {
	
	private static final Logger log = LoggerFactory.getLogger(SupportModule.class);
	
	@Provides
	@Singleton
	@Named("mail-properties")
	Properties emailProperties() {
		final Properties properties = new Properties();
		try {
			properties.load(SupportModule.class.getClassLoader().getResourceAsStream("email.properties"));
		} catch (FileNotFoundException e) {
			log.error("The configuration file email.properties can not be found", e);
		} catch (IOException e) {
			log.error("I/O Exception during loading configuration", e);
		}
		return properties;
	}
	

	@Inject
	@Singleton
	@Provides
	EmailSender emailSender(
			@Named("mail-properties") Properties emailPropertis) {
		return new EmailSender(emailPropertis);
	}
	
	@Singleton
	@Provides
	BWImageProcessor bwImageProcessor() {
		return new BWImageProcessor();
	}
}
