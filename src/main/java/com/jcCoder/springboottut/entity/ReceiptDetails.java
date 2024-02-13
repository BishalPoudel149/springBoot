package com.jcCoder.springboottut.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDetails {
    private String receiptId;
    private String mailSubject;
    private String msgBody;
    private String attachment;
    private String ccId;
}
