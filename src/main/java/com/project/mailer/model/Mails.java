package com.project.mailer.model;


import com.project.mailer.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MAILS")
public class Mails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tracking_id")
    private Long trackingId;

    @Column(name = "recepient_email")
    private String recepientEmail;

    @Column(name = "recepient_name")
    private String recepientName;

    @Lob
    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "mail_status")
    @Enumerated(EnumType.STRING)
    private Status mailStatus;
}
