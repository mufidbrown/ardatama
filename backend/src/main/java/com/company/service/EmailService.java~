package com.company.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String email, String resetLink) {
        String subject = "Reset Your Password";
        String body = buildEmailTemplate(resetLink);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    private String buildEmailTemplate(String resetLink) {
        return """
            <div style="font-family: Arial, sans-serif; text-align: center; padding: 20px; background-color: #f4f4f4;">
                <div style="max-width: 500px; margin: auto; background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px #ccc;">
                    <h2 style="color: #333;">Password Reset Request</h2>
                    <p style="font-size: 16px; color: #555;">
                        You requested a password reset. Click the button below to reset your password.
                    </p>
                    <a href="%s" 
                       style="display: inline-block; background-color: #007bff; color: white; text-decoration: none; 
                              padding: 12px 20px; font-size: 16px; border-radius: 5px; margin-top: 20px;">
                        Reset Password
                    </a>
                    <p style="margin-top: 20px; font-size: 14px; color: #777;">
                        If you did not request this, please ignore this email.
                    </p>
                </div>
            </div>
            """.formatted(resetLink);
    }
}