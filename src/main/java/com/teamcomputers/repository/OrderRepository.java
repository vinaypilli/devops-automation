package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teamcomputers.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
