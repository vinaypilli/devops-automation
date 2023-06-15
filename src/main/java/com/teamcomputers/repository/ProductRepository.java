package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teamcomputers.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
