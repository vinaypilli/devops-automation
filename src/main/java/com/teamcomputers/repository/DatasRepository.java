package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Data;

public interface DatasRepository extends JpaRepository<Data, Long> {

}
