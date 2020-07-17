package com.project.mailer.controller;

import com.project.mailer.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class CronController {

    @Autowired
    MailerService mailerService;

    @Scheduled(fixedDelay = 60000)
    public void mailCron() {

    }
}
