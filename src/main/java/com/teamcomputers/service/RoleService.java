package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.RoleDto;
import com.teamcomputers.dto.RoleFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.RoleEntity;
import com.teamcomputers.repository.RoleRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	public RoleEntity add(RoleDto roleDto) {

		RoleEntity role = new RoleEntity();

		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setRoleDescription(roleDto.getRoleDescription());
		role.setCreatedBy(userRepository.findById((int) (roleDto.getCreatedBy())).orElse(null));		
		role.setUpdatedBy(userRepository.findById((int) (roleDto.getUpdatedBy())).orElse(null));		
		role.setUpdatedDate(roleDto.getUpdatedDate());
		role.setStatus(roleDto.isStatus());
		return roleRepository.save(role);
	}

	public RoleEntity update(RoleDto roleDto) {
		RoleEntity role = new RoleEntity();

		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setRoleDescription(roleDto.getRoleDescription());
		role.setCreatedBy(userRepository.findById((int) (roleDto.getCreatedBy())).orElse(null));		
		role.setUpdatedBy(userRepository.findById((int) (roleDto.getUpdatedBy())).orElse(null));		
		role.setUpdatedDate(roleDto.getUpdatedDate());
		role.setStatus(roleDto.isStatus());
		return roleRepository.save(role);
	}

	public List<RoleFilterDto> getActiveUsers() {
		List<RoleEntity> roletEntity = roleRepository.findByStatusOrderByRoleNameAsc(true);
		List<RoleFilterDto> roleFilterDto = new ArrayList<>();

		for (RoleEntity roleTitle : roletEntity) {
			RoleFilterDto roleservice = new RoleFilterDto();

			roleservice.setRoleId(roleTitle.getRoleId());
			roleservice.setRoleName(roleTitle.getRoleName());
			roleFilterDto.add(roleservice);
		}

		return roleFilterDto;
	}

	public RoleEntity getById(int roleId) {
		return roleRepository.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Id :" + roleId + "Unavailable"));
	}

	public List<RoleEntity> getAll() {
		return roleRepository.findAll();
	}

	public boolean deleteById(int roleId) throws NotFoundException {
		RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(
				() -> new ResourceNotFoundException("customer Id : " + roleId + " is Not Present in DataBase"));
		roleEntity.setStatus(false); // Update status to false
		roleRepository.save(roleEntity);
		return true;
	}

}
