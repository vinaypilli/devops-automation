package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.ServiceFilterDto;
import com.teamcomputers.dto.ServiceTitleDto;
import com.teamcomputers.dto.ServicePriorityDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.ServiceTitleEntity;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.repository.SLATimelinesRepository;
import com.teamcomputers.repository.ServiceTitleRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class ServiceTitleService {

	@Autowired
	private ServiceTitleRepository serviceTitleRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private SLATimelinesRepository slaTimelinesRepository;
	@Autowired
	private UserRepository userRepository;
	

	public ServiceTitleEntity add(ServiceTitleDto serviceTitleDto) throws DuplicateUserException {

		ServiceTitleEntity userDup = serviceTitleRepository.findByServiceName(serviceTitleDto.getServiceName());
		if (userDup != null) {
			throw new DuplicateUserException("Servicename already exists");
		}

		ServiceTitleEntity serviceTitleEntity = new ServiceTitleEntity();

		serviceTitleEntity.setServiceId(serviceTitleDto.getServiceId());
		serviceTitleEntity.setServiceName(serviceTitleDto.getServiceName());
		serviceTitleEntity.setCreatedBy(userRepository.findById((int) (serviceTitleDto.getCreatedBy())).orElse(null));
		serviceTitleEntity.setUpdatedBy(userRepository.findById((int) (serviceTitleDto.getUpdatedBy())).orElse(null));
		serviceTitleEntity.setCreatedDate(serviceTitleDto.getCreatedDate());
		serviceTitleEntity.setUpdatedDate(serviceTitleDto.getUpdatedDate());
		serviceTitleEntity.setDefaultPriority(slaTimelinesRepository.findById(serviceTitleDto.getDefaultPriority()).orElse(null));

		serviceTitleEntity.setStatus(serviceTitleDto.isStatus());

		serviceTitleEntity.setSubCategory(subCategoryRepository.findById(serviceTitleDto.getSubCategoryId()).orElse(null));

		
//		SubCategory sub =subCategoryRepository.findById()
//		SubCategory cat = subCategoryRepository.findById(serviceTitleDto.getSubCategoryId()).orElse(null);
//		serviceTitleEntity.setSubCategoryId(cat.getSubCategoryId());
//		serviceTitleEntity.setSubCategoryName(cat.getSubCategoryName());
//		serviceTitleEntity.setCategoryId(cat.getCategoryId());
//		serviceTitleEntity.setCategoryName(cat.getCategoryName());

		return serviceTitleRepository.save(serviceTitleEntity);
	}

	public ServiceTitleEntity getById(int serviceId) {
		return serviceTitleRepository.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service Id :" + serviceId + "Unavailable"));
	}

	public List<ServicePriorityDto> getPriorityById(int serviceId) {
		List<ServiceTitleEntity> serviceTitleEntity = serviceTitleRepository.findByStatus(true);
		List<ServicePriorityDto> serviceFilterDto = new ArrayList<>();

		for (ServiceTitleEntity serviceTitle : serviceTitleEntity) {
			ServicePriorityDto filteredservice = new ServicePriorityDto();
			filteredservice.setDefaultPriority(serviceTitle.getDefaultPriority());
			serviceFilterDto.add(filteredservice);
		}
		return  serviceFilterDto;
	}

	
	public List<ServiceFilterDto> getActiveUsers() {
		List<ServiceTitleEntity> serviceTitleEntity = serviceTitleRepository.findByStatus(true);
		List<ServiceFilterDto> serviceFilterDto = new ArrayList<>();

		for (ServiceTitleEntity serviceTitle : serviceTitleEntity) {
			ServiceFilterDto filteredservice = new ServiceFilterDto();

			filteredservice.setServiceId(serviceTitle.getServiceId());
			filteredservice.setServiceName(serviceTitle.getServiceName());
			serviceFilterDto.add(filteredservice);
		}

		return serviceFilterDto;
	}

	public List<ServiceFilterDto> getAllActiveServiceTitlesBySubCategoryId(int subCategoryId) {

		SubCategory subCategory = new SubCategory();
		subCategory.setSubCategoryId(subCategoryId);
		List<ServiceTitleEntity> subCategories = serviceTitleRepository
				.findBySubCategoryAndStatusTrueOrderByServiceNameAsc(subCategory);
		List<ServiceFilterDto> filteredSubCategories = new ArrayList<>();

		for (ServiceTitleEntity serviceTitle : subCategories) {
			ServiceFilterDto filteredCategory = new ServiceFilterDto();

			filteredCategory.setServiceId(serviceTitle.getServiceId());
			filteredCategory.setServiceName(serviceTitle.getServiceName());
			filteredSubCategories.add(filteredCategory);
		}

		return filteredSubCategories;

	}

	public List<ServiceTitleEntity> getAll() {
		return serviceTitleRepository.findAllByOrderByServiceIdAsc();
	}
	
	public List<ServicePriorityDto> findPriorityNameByServiceId(int serviceId) {
		List<ServiceTitleEntity> serviceTitleEntity = serviceTitleRepository.findPriorityNameByServiceId(serviceId);
 		List<ServicePriorityDto> filteredSubCategories = new ArrayList<>();

 		for (ServiceTitleEntity serviceTitle : serviceTitleEntity) {
 			ServicePriorityDto filteredCategory = new ServicePriorityDto();

 			filteredCategory.setDefaultPriority(serviceTitle.getDefaultPriority());
 			filteredSubCategories.add(filteredCategory);
 		}
 		return filteredSubCategories;
 		
    }
	

	public ServiceTitleEntity update(ServiceTitleDto serviceTitleDto) {

		ServiceTitleEntity serviceTitleEntity = new ServiceTitleEntity();

		serviceTitleEntity.setServiceId(serviceTitleDto.getServiceId());
		serviceTitleEntity.setServiceName(serviceTitleDto.getServiceName());
		serviceTitleEntity.setCreatedDate(serviceTitleDto.getCreatedDate());
		serviceTitleEntity.setCreatedBy(userRepository.findById((int) (serviceTitleDto.getCreatedBy())).orElse(null));
		serviceTitleEntity.setUpdatedBy(userRepository.findById((int) (serviceTitleDto.getUpdatedBy())).orElse(null));
		serviceTitleEntity.setUpdatedDate(serviceTitleDto.getUpdatedDate());
		serviceTitleEntity.setDefaultPriority(slaTimelinesRepository.findById(serviceTitleDto.getDefaultPriority()).orElse(null));

		serviceTitleEntity.setStatus(serviceTitleDto.isStatus());

		serviceTitleEntity.setSubCategory(subCategoryRepository.findById(serviceTitleDto.getSubCategoryId()).orElse(null));
		return serviceTitleRepository.save(serviceTitleEntity);
	}

	public boolean deleteById(int serviceId) throws NotFoundException {
		ServiceTitleEntity serviceTitleEntity = serviceTitleRepository.findById(serviceId).orElseThrow(
				() -> new ResourceNotFoundException("serviceId: " + serviceId + " is Not Present in DataBase"));
		serviceTitleEntity.setStatus(false); // Update status to false
		serviceTitleRepository.save(serviceTitleEntity);
		return true;
	}
}
