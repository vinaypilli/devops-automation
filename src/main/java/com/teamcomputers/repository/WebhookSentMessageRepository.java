package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.OutgoingMessage;

public interface WebhookSentMessageRepository extends JpaRepository<OutgoingMessage, Long> {

}
