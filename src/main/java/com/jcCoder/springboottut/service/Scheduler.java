package com.jcCoder.springboottut.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

 //   @Scheduled(cron ="0 30,40 * ? * *")    --> To Schedule the function at every 30th and 40th minute of any hour

//     @Scheduled(fixedRate = 5000)  --> To trigger the scheduler to run every two seconds

   // @Scheduled(fixedDelay = 4000, initialDelay = 5000)
    public void ScheduledTask() {

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

        String strDate=dateFormat.format(new Date());

        System.out.println("Cron job Scheduler: Job Running at-"+strDate);

    }
}
