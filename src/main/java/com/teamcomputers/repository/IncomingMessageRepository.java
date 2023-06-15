package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.IncomingMessage;
@Repository
public interface IncomingMessageRepository extends JpaRepository<IncomingMessage, Long> {

}
