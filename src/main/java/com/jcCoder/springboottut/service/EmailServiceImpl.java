package com.jcCoder.springboottut.service;


import com.jcCoder.springboottut.entity.ReceiptDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public String senEmailService(ReceiptDetails receiptDetails) {
        try{

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            //setting necessary details
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(receiptDetails.getReceiptId());
            simpleMailMessage.setSubject(receiptDetails.getMailSubject());
            simpleMailMessage.setText(receiptDetails.getMsgBody());
            javaMailSender.send(simpleMailMessage);

            return "Mail Sent Successfully";



        }catch (Exception e){
            return "Error While sending Mail";
        }
    }
}
