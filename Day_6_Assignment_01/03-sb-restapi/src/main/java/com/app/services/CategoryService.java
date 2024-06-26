package com.app.services;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Category;
import com.app.repositories.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Override
	@Transactional 
	public void createCategory(Category category) {
		log.info("CategoryService ::createCategory {} ",category.toString());
		 categoryRepository.save(category);
		 log.info("CategoryService ::createCategory :: Category saved Successfully"); 
	}

	@Override
	public Category fetchCategoryById(Long id) {
		log.info("CategoryService ::fetchCategoryById :: ID  {} ",id);
		Category category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category Not Found"));
		log.info("CategoryService ::fetchCategoryById :: Fetched Category Data  {} ",category.getName());
		return category;
	}

	@Override
	public List<Category> fetchAllCategory() {
		log.info("CategoryService ::fetchAllCategory");
		List<Category> categoryList =categoryRepository.findAll();
		List<Category> sortedByNameCategoryList= categoryList.stream().sorted(Comparator.comparing(Category :: getName)).toList();
		log.info("CategoryService ::fetchAllCategory :: Category List Data in Database {} ",categoryList);
		return sortedByNameCategoryList;
	}

	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Override
	@Transactional 
	public void updateCategory(Long id, Category categoryInput) {
		log.info("CategoryService ::updateCategory :: id & category data {} {} ",id , categoryInput.toString());
		Category category = fetchCategoryById(id);
		category.setName(categoryInput.getName());
		categoryRepository.save(category);
		log.info("CategoryService ::updateCategory :: Updated Data saved successfully");
	}

	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Override
	@Transactional 
	public void deleteCategory(Long id) {
		log.info("CategoryService ::deleteCategory :: ID {} ",id);
		if(categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
		}else {
			log.error("Category Not found");
		    throw new RuntimeException("Category with this ID is not available in Database"); 
		}
	}
	/**
	 *@Transactional  It is optional when we are dealing with Single Table,
	 *But when we are dealing with Multiple table in Single Method then we use this
	 *  It is optional to write when single table , JPA will handle it internally
	 * */
	@Override
	@Transactional 
	public void deleteCategory_another(Long id) {
		log.info("CategoryService ::deleteCategory :: ID {} ",id);
		if(categoryRepository.existsById(id)) {
			Category category = fetchCategoryById(id);
			categoryRepository.delete(category);
			log.info("CategoryService ::deleteCategory :: Category with given Id is Deleted Successfully :: ID {} ", id );
		}else {
			log.error("Category Not found");
		    throw new RuntimeException("Category with this ID is not available in Database"); 
		}
	}

}
