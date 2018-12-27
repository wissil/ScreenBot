package com.util.ai.screenbot.support.tests.email;

import javax.mail.MessagingException;

import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;
import com.util.ai.screenbot.support.email.EmailSender;
import com.util.ai.screenbot.support.tests.config.SupportTestBase;


public class EmailSenderTest extends SupportTestBase {

	@Inject
	private EmailSender sender;
	
    @Test
    @Ignore
    public void shouldSendEmailTest() throws MessagingException {
    		final String fileName = "../logs/daily/main.log";
    	
    		System.out.println("Sending ...");
    		sender.send(fileName);
    		System.out.println("Sent!");
    }
}
