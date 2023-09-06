package com.example.consultationWebBacked.service;

import com.example.consultationWebBacked.DTO.MailDTO;
import com.example.consultationWebBacked.entity.Mails;
import com.example.consultationWebBacked.repositories.MailRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private ModelMapper modelMapper;

    private final JavaMailSender javaMailSender;

    private final MailRepo mailRepo;
    @Autowired
    public MailService(JavaMailSender javaMailSender, MailRepo mailRepo) {
        this.javaMailSender = javaMailSender;
        this.mailRepo = mailRepo;
    }
    public void sendEmail(MailDTO mailDTO) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(mailDTO.getSubject());
            message.setTo(mailDTO.getTomail());
            message.setFrom("wegapitiya1234@gmail.com");
            message.setText(mailDTO.getMessage());

            javaMailSender.send(message);
            Mails mails = new Mails();
            mails.setSubject(mailDTO.getSubject());
            mails.setTomail(mailDTO.getTomail());
            mails.setMessage(mailDTO.getMessage());

            // Save the email entity to the repository
            mailRepo.save(mails);

        } catch (Exception e) {
            // Handle the exception here, you can log it or rethrow it as needed.
            e.printStackTrace();
        }
    }
}
