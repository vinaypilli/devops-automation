package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.Order_Product;

public interface OrderProductRepository extends JpaRepository<Order_Product, Long>{

}
