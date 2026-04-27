package com.klu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("akshayaputti21@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Virtual Art Gallery - Your OTP Code");
        message.setText("Hello,\n\nYour One-Time Password (OTP) for verification is: " + otp + 
                        "\n\nThis code is valid for 5 minutes. Do not share it with anyone.\n\nRegards,\nVirtual Art Gallery Team");
        
        mailSender.send(message);
    }
}
