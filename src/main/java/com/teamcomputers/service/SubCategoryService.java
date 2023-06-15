package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.SubCategoryDto;
import com.teamcomputers.dto.SubCategoryFilterDto;
import com.teamcomputers.exception.DuplicateUserException;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Category;
import com.teamcomputers.model.SubCategory;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.SubCategoryRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;

	public SubCategory add(SubCategoryDto subCategoryDto) throws DuplicateUserException {

		SubCategory userDup = subCategoryRepository.findBySubCategoryName(subCategoryDto.getSubCategoryName());
		if (userDup != null) {
			throw new DuplicateUserException("Username already exists");
		}

		SubCategory subCategory = new SubCategory();

		subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
		subCategory.setCreatedBy(userRepository.findById((int)(subCategoryDto.getCreatedBy())).orElse(null));
		subCategory.setCreatedDate(subCategoryDto.getCreatedDate());
		subCategory.setUpdatedBy(userRepository.findById((int)(subCategoryDto.getUpdatedBy())).orElse(null));
		subCategory.setUpdatedDate(subCategoryDto.getUpdatedDate());
		subCategory.setStatus(subCategoryDto.isStatus());
		subCategory.setCategory(categoryRepository.findById((long) subCategoryDto.getCategoryId()).orElse(null));

		return subCategoryRepository.save(subCategory);
	}

	public SubCategory getById(int subCategoryId) {
		return subCategoryRepository.findById(subCategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("SubCategory Id :" + subCategoryId + "Unavailable"));
	}

	public List<SubCategory> getAll() {
		return subCategoryRepository.findAll();
	}

	public List<SubCategoryFilterDto> getActiveUsers() {
		List<SubCategory> subCategories = subCategoryRepository.findByStatus(true);
		List<SubCategoryFilterDto> filteredCategories = new ArrayList<>();

		for (SubCategory subcategory : subCategories) {
			SubCategoryFilterDto filteredCategory = new SubCategoryFilterDto();

			filteredCategory.setSubCategoryId(subcategory.getSubCategoryId());
			filteredCategory.setSubCategoryName(subcategory.getSubCategoryName());
			filteredCategories.add(filteredCategory);
		}

		return filteredCategories;
	}

	public List<SubCategoryFilterDto> getAllActiveSubcategoriesByCategoryId(Long categoryId) {

		Category category = new Category();
		category.setCategoryId(categoryId);
		List<SubCategory> subCategories = subCategoryRepository
				.findByCategoryAndStatusTrueOrderBySubCategoryNameAsc(category);
		List<SubCategoryFilterDto> filteredCategories = new ArrayList<>();

		for (SubCategory subcategory : subCategories) {
			SubCategoryFilterDto filteredCategory = new SubCategoryFilterDto();

			filteredCategory.setSubCategoryId(subcategory.getSubCategoryId());
			filteredCategory.setSubCategoryName(subcategory.getSubCategoryName());
			filteredCategories.add(filteredCategory);
		}

		return filteredCategories;

	}

	public SubCategory update(SubCategoryDto subCategoryDto) {

		SubCategory subCategory = new SubCategory();

		subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
		subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
		subCategory.setCreatedBy(userRepository.findById((int)(subCategoryDto.getCreatedBy())).orElse(null));
		subCategory.setCreatedDate(subCategoryDto.getCreatedDate());
		subCategory.setUpdatedBy(userRepository.findById((int)(subCategoryDto.getUpdatedBy())).orElse(null));
		subCategory.setUpdatedDate(subCategoryDto.getUpdatedDate());
		subCategory.setStatus(subCategoryDto.isStatus());
//		subCategory.setCategory(categoryRepository.findById((long) subCategoryDto.getCategoryId()).orElse(null));
		subCategory.setCategory(categoryRepository.findById((long) subCategoryDto.getCategoryId()).orElse(null));
		return subCategoryRepository.save(subCategory);
	}

	public boolean deleteById(int id) throws NotFoundException {
		SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("SubCategory Id: " + id + "is Not Present in Database"));
		subCategory.setStatus(false);
		subCategoryRepository.save(subCategory);
		return true;
	}

}
