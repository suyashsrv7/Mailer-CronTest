package com.project.mailer.repository;

import com.project.mailer.model.Mails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mails, Long> {
}
