package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamcomputers.model.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
