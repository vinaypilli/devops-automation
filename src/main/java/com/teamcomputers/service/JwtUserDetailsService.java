package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.NewUserFilterDto;
import com.teamcomputers.dto.UserFilterDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.model.UserDao;
import com.teamcomputers.model.UserDto;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.DepartmentRepository;
import com.teamcomputers.repository.RoleRepository;
import com.teamcomputers.repository.ServiceTitleRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private ServiceTitleRepository serviceTitleRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public UserDao getUserByUsername(String username) throws UsernameNotFoundException {
		UserDao user = userDao.findByUsername(username);
		return user;
	}

//	public UserDao save(UserDto user) throws DuplicateUserException {
//
//		UserDao userDup = userDao.findByUsername(user.getUsername());
//		if (userDup != null) {
//			throw new DuplicateUserException("Username already exists");
//		}
//
//		// SubCategory subCategory = new SubCategory();
//		UserDao newUser = new UserDao();
//
//		newUser.setUsername(user.getUsername());
//		newUser.setFirstName(user.getFirstName());
//		newUser.setLastName(user.getLastName());
//		newUser.setEmail(user.getEmail());
//		newUser.setContact(user.getContact());
//		newUser.setAddress(user.getAddress());
//		newUser.setState(user.getState());
//		newUser.setCity(user.getCity());
//		newUser.setPostcode(user.getPostcode());
//		newUser.setCreatedBy(user.getCreatedBy());
//		newUser.setCreatedDate(user.getCreatedDate());
//		newUser.setUpdatedBy(user.getUpdatedBy());
//		newUser.setUpdatedDate(user.getUpdatedDate());
//		newUser.setStatus(user.isStatus());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		newUser.setDepartment(departmentRepo.findById((int) user.getDepartmentId()).orElse(null));
//
//		DepartmentEntity cat = departmentRepo.findById((int) user.getDepartmentId()).orElse(null);
//		newUser.setDepartmentName(cat.getDepartmentName());
//		newUser.setDepartmentId(cat.getDepartmentId());
//		newUser.setRole(roleRepository.findById(user.getRoleId()).orElse(null));
//
//		RoleEntity cat1 = roleRepository.findById(user.getRoleId()).orElse(null);
//		newUser.setRoleName(cat1.getRoleName());
//		newUser.setRoleId(cat1.getRoleId());
//
//		return userDao.save(newUser);
//	}

	public UserDao save(UserDto user) throws DuplicateUserException {
		UserDao userDup = userDao.findByUsername(user.getUsername());
		if (userDup != null){
			throw new DuplicateUserException("Username already exists");
		}
		
		UserDao newUser = new UserDao();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setContact(user.getContact());
		newUser.setAddress(user.getAddress());
		newUser.setState(user.getState());
		newUser.setPostcode(user.getPostcode());
		newUser.setCity(user.getCity());
		newUser.setCreatedBy(user.getCreatedBy());
		newUser.setCreatedDate(user.getCreatedDate());
		newUser.setUpdatedBy(user.getUpdatedBy());
		newUser.setUpdatedDate(user.getUpdatedDate());
		newUser.setStatus(user.isStatus());
		newUser.setDepartment(departmentRepo.findById((int) user.getDepartmentId()).orElse(null));
		newUser.setRole(roleRepository.findById((int) user.getRoleId()).orElse(null));
		newUser.setCategory(categoryRepository.findById((long) user.getCategoryId()).orElse(null));
		newUser.setSubCategory(subCategoryRepository.findById((int) user.getSubCategoryId()).orElse(null));
		newUser.setServiceTitle(serviceTitleRepository.findById((int) user.getServiceTitleId()).orElse(null));
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userDao.save(newUser);
	}

	public List<UserDao> getAll() {
		return userDao.findAll();
	}

	public List<UserFilterDto> getActiveUsers() {
		List<UserDao> user = userDao.findByStatusOrderByUsernameAsc(true);
		List<UserFilterDto> filteredCategories = new ArrayList<>();

		for (UserDao userDao : user) {
			UserFilterDto filtered = new UserFilterDto();

			filtered.setUserId(userDao.getUserId());
			filtered.setFirstName(userDao.getFirstName());
			filtered.setLastName(userDao.getLastName());
			filtered.setUsername(userDao.getUsername());
			filtered.setRole(userDao.getRole());
			filteredCategories.add(filtered);
		}

		return filteredCategories;
	}
//	public List<NewUserFilterDto> getAllActiveUsersByDepartmentId(Integer id) {
//		List<UserDao> user = userDao.findByDepartmentAndStatusTrue(id);
//		List<NewUserFilterDto> filteredUsers = new ArrayList<>();
//
//		for (UserDao userDao : user) {
//			NewUserFilterDto filtered = new NewUserFilterDto();
//
//			filtered.setUserId(userDao.getUserId());
//			filtered.setUsername(userDao.getUsername());
//			filtered.setFirstName(userDao.getFirstName());
//			filtered.setLastName(userDao.getLastName());
//			filteredUsers.add(filtered);
//		}
//
//		return filteredUsers;
//	}
	public List<UserFilterDto> getAllActiveUsersByDepartmentId(int departmentId) {

		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDepartmentId(departmentId);
		List<UserDao> userdao = userDao
				.findByDepartmentAndStatusTrueOrderByUsernameAsc(departmentEntity);
		List<UserFilterDto> filteredCategories = new ArrayList<>();

		for (UserDao userdao1 : userdao) {
			UserFilterDto filteredCategory = new UserFilterDto();

			filteredCategory.setUserId(userdao1.getUserId());
			filteredCategory.setUsername(userdao1.getUsername());
			filteredCategory.setFirstName(userdao1.getFirstName());
			filteredCategory.setLastName(userdao1.getLastName());
			filteredCategory.setRole(userdao1.getRole());
			//filteredCategory.setRoleName(userdao1.getRole().getRoleName());
			
			filteredCategories.add(filteredCategory);
		}

		return filteredCategories;

	}


	public UserDao getById(int userId) {
		return userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Id :" + userId + "Unavailable"));

	}

	public UserDao updateUser(UserDto user) throws ResourceNotFoundException {
		UserDao newUser = userDao.findById(user.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setContact(user.getContact());
		newUser.setAddress(user.getAddress());
		newUser.setState(user.getState());
		newUser.setCity(user.getCity());
		newUser.setCreatedBy(user.getCreatedBy());
		newUser.setPostcode(user.getPostcode());
		newUser.setCreatedDate(user.getCreatedDate());
		newUser.setUpdatedBy(user.getUpdatedBy());
		newUser.setUpdatedDate(user.getUpdatedDate());
		newUser.setStatus(user.isStatus());
		newUser.setDepartment(departmentRepo.findById((int) user.getDepartmentId()).orElse(null));
		newUser.setRole(roleRepository.findById((int) user.getRoleId()).orElse(null));
		newUser.setCategory(categoryRepository.findById((long) user.getCategoryId()).orElse(null));
		newUser.setSubCategory(subCategoryRepository.findById((int) user.getSubCategoryId()).orElse(null));
		newUser.setServiceTitle(serviceTitleRepository.findById((int) user.getServiceTitleId()).orElse(null));

		return userDao.save(newUser);
	}

	public boolean deleteById(int userId) throws NotFoundException {
		UserDao user = userDao.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("user Id : " + userId + " is Not Present in DataBase"));
		user.setStatus(false); // Update status to false
		userDao.save(user);
		return true;
	}

}