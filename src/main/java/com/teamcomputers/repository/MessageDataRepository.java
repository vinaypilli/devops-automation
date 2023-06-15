package com.teamcomputers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.MessageData;

public interface MessageDataRepository extends JpaRepository<MessageData, Long>{
	
	MessageData findByMid(Long mid);

}
