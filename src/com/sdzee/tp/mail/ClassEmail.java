package com.sdzee.tp.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ClassEmail {

	private Properties props;
	private final String emailsource = "projetnoreply@gmail.com";
	private final String password = "noreplyprojet";
	@SuppressWarnings("unused")
	private String emaildest;
	@SuppressWarnings("unused")
	private String message;

	public ClassEmail(String emaildest, String message) {
		super();
		this.emaildest = emaildest;
		this.message = message;
		props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailsource, password);
			}
		});

		try {

			Message messag = new MimeMessage(session);
			messag.setFrom(new InternetAddress("projetnoreply@gmail.com"));
			messag.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emaildest));
			messag.setSubject("Message de validation");
			messag.setText(message);

			Transport.send(messag);

			System.out.println("Message envoyé avec succes");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
