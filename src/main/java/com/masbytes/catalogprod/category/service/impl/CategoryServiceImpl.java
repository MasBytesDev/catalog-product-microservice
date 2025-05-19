package com.masbytes.catalogprod.category.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.CategoryResponseDTO;
import com.masbytes.catalogprod.category.dto.UpdateCategoryDTO;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyExistsException;
import com.masbytes.catalogprod.category.exception.validation.CategoryInvalidDataException;
import com.masbytes.catalogprod.category.mapper.CategoryMapper;
import com.masbytes.catalogprod.category.model.Category;
import com.masbytes.catalogprod.category.repository.CategoryRepository;
import com.masbytes.catalogprod.category.service.CategoryService;
import com.masbytes.catalogprod.category.validation.CategoryValidator;
import com.masbytes.catalogprod.enums.Status;

import jakarta.transaction.Transactional;

/**
 * CategoryServiceImpl
 * 
 * This class implements the CategoryService interface and provides
 * the business logic for managing categories.
 * It uses the CategoryRepository to interact with the database and
 * the CategoryMapper to map between entities and DTOs.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Creates a new category.
     * 
     * Validates the input data, checks if a category with the same name already
     * exists,
     * and then persists the new category to the database.
     *
     * @param dto The CategoryRequestDTO containing the category data.
     * @return The created CategoryResponseDTO.
     * @throws CategoryInvalidDataException   If the input data is null or invalid.
     * @throws CategoryAlreadyExistsException If a category with the same name
     *                                        already exists.
     */

    @Override
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {

        // Validate the input data
        CategoryValidator.validateCategoryRequest(dto);

        // Normalize the category name
        // by trimming and converting it to uppercase        
        String normalizedName = dto.getName().trim().toUpperCase(Locale.ROOT);

        // Check if a category with the same name already exists
        // using the normalized name
        if (categoryRepository.existsByName(normalizedName)) {
            throw new CategoryAlreadyExistsException("Category with name " + normalizedName + " already exists");
        }

        // Create a new Category entity from the DTO
        Category category = CategoryMapper.toEntity(dto);

        // Save the new category to the database
        // and return the response DTO
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toResponseDTO(savedCategory);

    }

    @Override
    public Optional<CategoryResponseDTO> getCategoryById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    @Override
    public Optional<CategoryResponseDTO> getCategoryByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryByName'");
    }

    @Override
    public List<CategoryResponseDTO> searchByPartialName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByPartialName'");
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, UpdateCategoryDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCategory'");
    }

    @Override
    public CategoryResponseDTO disableCategory(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disableCategory'");
    }

    @Override
    public CategoryResponseDTO enableCategory(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enableCategory'");
    }

    @Override
    public CategoryResponseDTO deleteCategory(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCategory'");
    }

    @Override
    public List<CategoryResponseDTO> getCategoryByStatus(Status status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryByStatus'");
    }

    @Override
    public Page<CategoryResponseDTO> getAllCategories(Pageable pageable, CategoryRequestDTO filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategories'");
    }

    // Implement the methods defined in the CategoryService interface

}
