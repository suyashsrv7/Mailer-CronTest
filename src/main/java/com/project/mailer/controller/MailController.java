package com.project.mailer.controller;

import com.project.mailer.dto.MailsListDTO;
import com.project.mailer.dto.TrackingIdDTO;
import com.project.mailer.model.Mails;
import com.project.mailer.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
public class MailController {

    @Autowired
    private MailerService mailService;

    @RequestMapping(value = "/mail-list/upload", method = RequestMethod.POST)
    public DeferredResult<TrackingIdDTO> mailListUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return getDeferredResult(mailService.handleCsvUpload(file).toCompletableFuture());
    }

    @RequestMapping(value = "/mail-info/{trackingId}", method = RequestMethod.GET)
    public DeferredResult<Mails> mailListUpload(@PathVariable long trackingId) throws IOException {
        return getDeferredResult(mailService.getMailInfoByTrackingId(trackingId).toCompletableFuture());
    }

    @RequestMapping(value = "/mail/{trackingId}/send", method = RequestMethod.GET)
    public DeferredResult<Mails> sendMail(@PathVariable long trackingId) {
        return getDeferredResult(mailService.sendMailByTrackingId(trackingId).toCompletableFuture());
    }

    @RequestMapping(value = "/mails", method = RequestMethod.GET)
    public DeferredResult<MailsListDTO> fetchAllMails() {
        return getDeferredResult(mailService.getAllMails().toCompletableFuture());
    }


    public static <T> DeferredResult<T> getDeferredResult(CompletableFuture<T> future) {
        DeferredResult<T> deferredResult = new DeferredResult<>(600000l);
        future.thenAccept(deferredResult::setResult);
        return deferredResult;
    }
}
