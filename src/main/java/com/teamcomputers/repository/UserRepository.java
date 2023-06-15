package com.teamcomputers.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.model.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Integer> {
   
	UserDao findByUsername(String username);
	
	List<UserDao> findByStatusOrderByUsernameAsc(boolean b);
	//List<UserDao> findAllByOrderByUsernameAsc();
	List<UserDao>findByDepartmentAndStatusTrueOrderByUsernameAsc( DepartmentEntity departmentEntity);
	
}