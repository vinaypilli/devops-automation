package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.DepartmentDto;
import com.teamcomputers.dto.DepartmentfilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.DepartmentEntity;
import com.teamcomputers.repository.DepartmentRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	public DepartmentEntity add(DepartmentDto departmentDto) {

		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setDepartmentId(departmentDto.getDepartmentId());
		departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
		departmentEntity.setDepartmentCode(departmentDto.getDepartmentCode());
		departmentEntity.setDepartmentHead(departmentDto.getDepartmentHead());
		departmentEntity.setDescription(departmentDto.getDescription());
		departmentEntity.setCreatedBy(userRepository.findById((int) (departmentDto.getCreatedBy())).orElse(null));		
		departmentEntity.setUpdatedBy(userRepository.findById((int) (departmentDto.getUpdatedBy())).orElse(null));
		departmentEntity.setCreatedDate(departmentDto.getCreatedDate());
		
		departmentEntity.setUpdatedDate(departmentDto.getUpdatedDate());

		return departmentRepository.save(departmentEntity);
	}

	public DepartmentEntity getById(int departmentId) {
		return departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("department Id : " + departmentId + " Unavailable"));
	}

	public List<DepartmentEntity> getAll() {
		return departmentRepository.findAll();
	}

	public DepartmentEntity update(DepartmentDto departmentDto) {

		DepartmentEntity departmentEntity = new DepartmentEntity();

		departmentEntity.setDepartmentId(departmentDto.getDepartmentId());
		departmentEntity.setDepartmentName(departmentDto.getDepartmentName());
		departmentEntity.setDepartmentCode(departmentDto.getDepartmentCode());
		departmentEntity.setDepartmentHead(departmentDto.getDepartmentHead());
		departmentEntity.setDescription(departmentDto.getDescription());
		departmentEntity.setCreatedBy(userRepository.findById((int) (departmentDto.getCreatedBy())).orElse(null));		
		departmentEntity.setUpdatedBy(userRepository.findById((int) (departmentDto.getUpdatedBy())).orElse(null));
		departmentEntity.setCreatedDate(departmentDto.getCreatedDate());
		
		departmentEntity.setUpdatedDate(departmentDto.getUpdatedDate());
		return departmentRepository.save(departmentEntity);
	}

	public List<DepartmentfilterDto> getActiveUsers() {
		List<DepartmentEntity> departmentEntity = departmentRepository.findByStatusOrderByDepartmentNameAsc(true);
		List<DepartmentfilterDto> sdepartmentFilterDto = new ArrayList<>();

		for (DepartmentEntity departmentTitle : departmentEntity) {
			DepartmentfilterDto departmentservice = new DepartmentfilterDto();

			departmentservice.setDepartmentId(departmentTitle.getDepartmentId());
			departmentservice.setDepartmentName(departmentTitle.getDepartmentName());
			sdepartmentFilterDto.add(departmentservice);
		}

		return sdepartmentFilterDto;
	}

	public boolean deleteById(int departmentId) throws NotFoundException {
		DepartmentEntity department = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("department Id : " + departmentId + " is Not Present in DataBase"));
		department.setStatus(false); // Update status to false
		departmentRepository.save(department);
		return true;
	}

//	public boolean deleteById(int id) {
//		departmentRepository.deleteById(id);
//		return true;
//	}
}
