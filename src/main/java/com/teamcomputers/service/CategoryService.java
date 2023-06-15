package com.teamcomputers.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.teamcomputers.dto.CategoryDto;
import com.teamcomputers.dto.CategoryFilterDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Category;
import com.teamcomputers.repository.CategoryRepository;
import com.teamcomputers.repository.UserRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Category add(CategoryDto categoryDto) {

	       	Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCreatedBy(userRepository.findById((int)(categoryDto.getCreatedBy())).orElse(null));
		category.setCreatedDate(categoryDto.getCreatedDate());
		category.setUpdatedBy(userRepository.findById((int)(categoryDto.getUpdatedBy())).orElse(null));
		category.setUpdatedDate(categoryDto.getUpdatedDate());
		category.setStatus(categoryDto.isStatus());

		return categoryRepository.save(category);
	}

	public Category getById(long customerId) {
		return categoryRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Id : " + customerId + " Unavailable"));
	}

	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	public List<CategoryFilterDto> getActiveUsers() {
  List<Category> categories = categoryRepository.findCategoryIdAndCategoryNameByStatusOrderByCategoryNameAsc(true);
  List<CategoryFilterDto> filteredCategories = new ArrayList<>();

    for (Category category : categories) {
	     CategoryFilterDto filteredCategory = new CategoryFilterDto();
         filteredCategory.setCategoryId(category.getCategoryId());
         filteredCategory.setCategoryName(category.getCategoryName());
         // filteredCategory.setStatus(category.isStatus());
         filteredCategories.add(filteredCategory);
  }

          return filteredCategories;
}
	    public Category update(CategoryDto categoryDto) {
		
		Category category = new Category();
		category.setCategoryId(categoryDto.getCategoryId());
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCreatedBy(userRepository.findById((int)(categoryDto.getCreatedBy())).orElse(null));
		category.setCreatedDate(categoryDto.getCreatedDate());
		category.setUpdatedBy(userRepository.findById((int)(categoryDto.getUpdatedBy())).orElse(null));
		category.setUpdatedDate(categoryDto.getUpdatedDate());
		category.setStatus(categoryDto.isStatus());

		return categoryRepository.save(category);
	}	
	
	public boolean deleteById(long categoryId) throws NotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category Id : " + categoryId + " Unavailable"));
		category.setStatus(false); // Update status to false
        categoryRepository.save(category);
		return true;
	}

//	public boolean deleteById(long id) {
//		categoryRepository.deleteById(id);
//		return true;
//	}

}
