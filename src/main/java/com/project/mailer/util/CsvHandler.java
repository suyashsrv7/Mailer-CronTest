package com.project.mailer.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.project.mailer.dto.MailTaskDTO;
import com.project.mailer.enums.Status;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CsvHandler {

    public static CompletionStage<List<MailTaskDTO>> extractDataFromCSV(MultipartFile csvFile){
        List<MailTaskDTO> mailList = new ArrayList<>();
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
                mailList.add(processCsvRow(line));
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        return CompletableFuture.completedFuture(mailList);
    }

    public static MailTaskDTO processCsvRow(String line) {
        String tmp[] = line.split(",");
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        System.out.println(tmp[2]);
        MailTaskDTO mailTask = MailTaskDTO.builder()
                .recepientName(tmp[0])
                .recepientEmail(tmp[1])
                .message(tmp[2])
                .createdAt(currentTime)
                .modifiedAt(currentTime)
                .mailStatus(Status.IN_QUEUE)
                .build();
        return mailTask;
    }
}
