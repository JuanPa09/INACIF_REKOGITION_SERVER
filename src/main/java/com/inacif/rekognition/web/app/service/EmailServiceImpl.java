package com.inacif.rekognition.web.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender emailSender;
	
	public void sendEmail(String to, String subject, String text) {
		MimeMessage message = emailSender.createMimeMessage();
		
        try {
        	MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
	        helper.setText(text, true); 
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        emailSender.send(message);
	}
	
}
