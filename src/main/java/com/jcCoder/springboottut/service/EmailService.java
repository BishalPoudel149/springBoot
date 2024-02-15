package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.ReceiptDetails;
import org.springframework.stereotype.Service;


public interface EmailService {
   public String senEmailService(ReceiptDetails receiptDetails);
}
