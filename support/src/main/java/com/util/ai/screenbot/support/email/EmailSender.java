package com.util.ai.screenbot.support.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.google.inject.name.Named;

public class EmailSender {
	
	private final Properties prop;

	private final String username;

	private final String password;

	private final String recipientEmails;
	
	private final String content;
	
	private final String subject;

	public EmailSender(
			@Named("mail-properties") Properties prop) {
		this.prop = prop;
		this.username = prop.getProperty("mail.username");
		this.password = prop.getProperty("mail.password");
		this.recipientEmails = prop.getProperty("mail.recipients");
		this.content = prop.getProperty("mail.content");
		this.subject = prop.getProperty("mail.subject");
	}

	public void send() throws MessagingException {
		Session session = Session.getInstance(prop, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});		

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", recipientEmails)));
		message.setSubject(subject);

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(content, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);
	}
}
