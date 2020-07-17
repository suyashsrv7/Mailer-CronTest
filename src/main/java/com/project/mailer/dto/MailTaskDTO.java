package com.project.mailer.dto;

import com.project.mailer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailTaskDTO {
    private String recepientEmail;
    private String recepientName;
    private String message;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Status mailStatus;
}
