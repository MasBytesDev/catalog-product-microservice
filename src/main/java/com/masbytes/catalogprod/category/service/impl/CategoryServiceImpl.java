package com.masbytes.catalogprod.category.service.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masbytes.catalogprod.category.dto.CategoryFilterDTO;
import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.CategoryResponseDTO;
import com.masbytes.catalogprod.category.dto.UpdateCategoryDTO;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyExistsException;
import com.masbytes.catalogprod.category.exception.database.CategoryNotFoundException;
import com.masbytes.catalogprod.category.exception.validation.CategoryInvalidDataException;
import com.masbytes.catalogprod.category.mapper.CategoryMapper;
import com.masbytes.catalogprod.category.model.Category;
import com.masbytes.catalogprod.category.repository.CategoryRepository;
import com.masbytes.catalogprod.category.service.CategoryService;
import com.masbytes.catalogprod.category.validation.CategoryValidator;
import com.masbytes.catalogprod.enums.Status;

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

    /**
     * Retrieves a category by its ID.
     * 
     * Validates the input ID, checks if the category exists in the database,
     * and returns the corresponding CategoryResponseDTO.
     * 
     * @param id The ID of the category to retrieve.
     * @return a CategoryResponseDTO containing the category data.
     * @throws CategoryInvalidDataException if the ID is null or invalid.
     */

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Long id) {

        // Check if the ID is null and throw an exception if it is
        if (id == null) {
            throw new CategoryInvalidDataException("Category ID cannot be null");
        }

        // Find the category by ID using the repository and map it to a response DTO
        // If the category is not found, throw a CategoryNotFoundException
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));

        // Map the found category to a response DTO
        // and return it wrapped in an Optional
        // If the category is null, return an empty Optional
        // Otherwise, return the mapped response DTO
        return CategoryMapper.toResponseDTO(category);
    }

    /**
     * Retrieves a category by its name.
     * 
     * Validates the input name, checks if the category exists in the database,
     * and returns the corresponding CategoryResponseDTO.
     * 
     * @param name The name of the category to retrieve.
     * @return a CategoryResponseDTO containing the category data.
     * @throws
     * CategoryInvalidDataException         if the name is null or empty.
     */

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryByName(String name) {
        // Check if the name is null or empty and throw an exception if it is
        if (name == null || name.trim().isEmpty()) {
            throw new CategoryInvalidDataException("Category name cannot be null or empty");
        }

        // Normalize the category name by trimming and converting it to uppercase
        // to ensure consistent comparison
        String normalizedName = name.trim().toUpperCase(Locale.ROOT);

        // Find the category by name using the repository and map it to a response DTO
        // If the category is not found, throw a CategoryNotFoundException
        Category category = categoryRepository.findByName(normalizedName)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + normalizedName));

        // Check if the category is deleted
        // If it is, throw a CategoryNotFoundException
        // to indicate that the category is not available
        // If the category is not deleted, return the mapped response DTO
        // If the category is deleted, throw a CategoryNotFoundException
        if (category.getStatus() == Status.DELETED) {
            throw new CategoryNotFoundException("Category with name " + normalizedName + " is deleted");
        }

        // Map the found category to a response DTO
        // and return it wrapped in an Optional
        return CategoryMapper.toResponseDTO(category);
    }

    /**
     * Searches for categories by partial name.
     * 
     * Validates the input name, checks if any categories match the partial name,
     * and returns a list of CategoryResponseDTOs containing the matching
     * categories.
     * 
     * @param name The partial name to search for.
     * @return a list of CategoryResponseDTOs containing the matching categories.
     * @throws CategoryInvalidDataException if the name is null or empty.
     * @throws CategoryNotFoundException    if no categories are found matching the
     *                                      partial name.
     */

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> searchByPartialName(String name) {
        // Check if the name is null or empty and throw an exception if it is
        // This method is used to search for categories by partial name
        if (name == null || name.trim().isEmpty()) {
            throw new CategoryInvalidDataException("Category name cannot be null or empty");
        }

        // Normalize the category name by trimming and converting it to uppercase
        // to ensure consistent comparison
        String normalizedName = name.trim();

        // Find categories by partial name using the repository
        // and map them to response DTOs
        List<Category> categories = categoryRepository.findByNameContainingIgnoreCase(normalizedName);

        // Check if any categories were found
        // If no categories were found, throw a CategoryNotFoundException
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found matching: " + normalizedName);
        }

        // Return the list of mapped response DTOs
        // by using the CategoryMapper to convert each category entity to a DTO
        return categories.stream()
                .map(CategoryMapper::toResponseDTO)
                .toList();
    }

    /**
     * Updates an existing category.
     * 
     * Validates the input ID and DTO, checks if the category exists in the
     * database,
     * and then updates the category with the new data.
     * 
     * @param id  The ID of the category to update.
     * @param dto The UpdateCategoryDTO containing the new category data.
     * @return The updated CategoryResponseDTO.
     * @throws CategoryInvalidDataException   If the ID is null or the DTO is null.
     * @throws CategoryNotFoundException      If the category with the given ID is not
     *                                        found
     */

    @Override
    @Transactional
    public CategoryResponseDTO updateCategory(Long id, UpdateCategoryDTO dto) {
        // Validate the input data
        // Check if the ID is null and throw an exception if it is
        if (id == null) {
            throw new CategoryInvalidDataException("Category ID cannot be null");
        }

        // Check if the DTO is null and throw an exception if it is
        // This method is used to update an existing category
        if (dto == null) {
            throw new CategoryInvalidDataException("Category data cannot be null");
        }

        // Normalize the category name by trimming and converting it to uppercase
        // to ensure consistent comparison
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id));

        // Update the category entity with the new data
        // using the CategoryMapper to convert the DTO to an entity
        CategoryMapper.updateEntity(category, dto);

        // Save the updated category to the database
        // and return the response DTO
        Category updatedCategory = categoryRepository.save(category);

        // Return the updated category as a response DTO
        // by using the CategoryMapper to convert the updated entity to a DTO
        return CategoryMapper.toResponseDTO(updatedCategory);
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
    public Page<CategoryResponseDTO> getAllCategories(Pageable pageable, CategoryFilterDTO filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCategories'");
    }

}
