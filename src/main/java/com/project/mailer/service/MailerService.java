package com.project.mailer.service;

import com.project.mailer.dto.MailTaskDTO;
import com.project.mailer.dto.TrackingIdDTO;
import com.project.mailer.model.Mails;
import com.project.mailer.repository.MailRepository;

import com.project.mailer.util.CsvHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class MailerService {

    @Autowired
    MailRepository mailRepository;

    public CompletionStage<TrackingIdDTO> handleCsvUpload(MultipartFile file)  {
        return CsvHandler.extractDataFromCSV(file)
                .thenCompose(mailingList -> {
                    List<Mails> newMails = new ArrayList<>();
                    mailingList.forEach(mailTask -> {
                        Mails newMailRequest = buildNewMailingRequest(mailTask);
                        newMails.add(newMailRequest);
                    });
                    return CompletableFuture.completedFuture(mailRepository.saveAll(newMails));
                })
                .thenCompose(savedMailingList -> {
                    return CompletableFuture.completedFuture(buildTrackingIdResponseList(savedMailingList));
                });
    }

    public CompletionStage<Mails> getMailInfoByTrackingId(Long trackingId) {
        Mails requestedMail = mailRepository.findById(trackingId).get();
        return CompletableFuture.completedFuture(requestedMail);
    }

    public Mails buildNewMailingRequest(MailTaskDTO mailRequest) {
        return new Mails().builder()
                .recepientEmail(mailRequest.getRecepientEmail())
                .recepientName(mailRequest.getRecepientName())
                .message(mailRequest.getMessage())
                .mailStatus(mailRequest.getMailStatus())
                .createdAt(mailRequest.getCreatedAt())
                .modifiedAt(mailRequest.getModifiedAt())
                .build();
    }

    public TrackingIdDTO buildTrackingIdResponseList(List<Mails> savedMailingList) {
        TrackingIdDTO trackingIdList = new TrackingIdDTO();
        trackingIdList.setTrackingIdList(savedMailingList.stream()
                .map(savedMail -> savedMail.getTrackingId())
                .collect(Collectors.toList()));
        return trackingIdList;
    }

}
