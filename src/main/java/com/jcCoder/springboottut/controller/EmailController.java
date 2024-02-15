package com.jcCoder.springboottut.controller;

import com.jcCoder.springboottut.entity.ReceiptDetails;
import com.jcCoder.springboottut.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/mail/sendmail")
    public String sendMail(@RequestBody ReceiptDetails receiptDetails){
    return emailService.senEmailService(receiptDetails);
    }

}