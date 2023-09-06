package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Mails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepo extends JpaRepository<Mails,Integer> {
}
