package com.masbytes.catalogprod.category.validation;

import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.UpdateCategoryDTO;
import com.masbytes.catalogprod.category.exception.validation.CategoryInvalidDataException;

/**
 * Utility class that provides validation methods for category-related
 * operations.
 * 
 * This class is used to validate the input data for creating or updating
 * categories. If the data is invalid or null, it throws custom validation
 * exceptions.
 * 
 * All methods are static and stateless.
 */

public class CategoryValidator {

    /**
     * Validates the data of a CategoryRequestDTO for category creation.
     * 
     * This method checks that the name and description are not null or blank. *
     *
     * @param dto the category data to validate
     * @throws CategoryInvalidDataException if the DTO is null, or if name or
     *                                      description are missing or blank
     */

    public static void validateCategoryRequest(CategoryRequestDTO dto) {
        if (dto == null) {
            throw new CategoryInvalidDataException("Category data cannot be null");
        }

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new CategoryInvalidDataException("Category name cannot be null or empty");
        }

        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            throw new CategoryInvalidDataException("Category description cannot be null or empty");
        }
    }

    /**
     * Validates the data of an UpdateCategoryDTO for category update.
     * 
     * This method checks that the DTO is not null. It does not enforce any field to
     * be present,
     * but ensures that if a field is present, it must be valid.
     *
     * @param dto the update data to validate
     * @throws CategoryInvalidDataException if the DTO is null or contains invalid
     *                                      fields
     */
    
    public static void validateUpdateCategoryDTO(UpdateCategoryDTO dto) {
        // implementación
    }

}
