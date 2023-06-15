package com.teamcomputers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teamcomputers.model.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
