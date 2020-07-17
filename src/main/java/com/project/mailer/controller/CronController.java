package com.project.mailer.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.project.mailer.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CronController {

    @Autowired
    MailerService mailerService;

    @Scheduled(fixedDelay = 60000)
    public void mailCron() {
        System.out.println("Starting cron job-----------");
        mailerService.startMailCron();
    }
}
