package com.masbytes.catalogprod.category.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.masbytes.catalogprod.category.dto.CategoryFilterDTO;
import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.CategoryResponseDTO;
import com.masbytes.catalogprod.category.dto.UpdateCategoryDTO;
import com.masbytes.catalogprod.category.exception.database.CategoryNotFoundException;
import com.masbytes.catalogprod.enums.Status;

public interface CategoryService {

    /**
     * Creates a new category.
     *
     * @param dto the dto containing the category details to be created.
     * @return the created category as a {@link CategoryResponseDTO}
     */

    CategoryResponseDTO createCategory(CategoryRequestDTO dto);

    /**
     * Retrieves a category by its ID.
     *
     * @param id the unique identifier of the category to retrieve
     * @return the category as a {@link CategoryResponseDTO}
     * @throws CategoryNotFoundException if no category with the given ID exists
     *         or if the category is logically deleted  
     */

    CategoryResponseDTO getCategoryById(Long id);

    /**
     * Retrieves a category by its name.
     * 
     * @param name the name of the category to retrieve
     * @return the category as a {@link CategoryResponseDTO}
     * @throws CategoryNotFoundException if no category with the given name exists
     *         or if the category is logically deleted
     */

    CategoryResponseDTO getCategoryByName(String name);

    /**
     * Searches for categories whose names contain the given partial string.
     *
     * @param name the partial name to search for
     * @return a list of {@link CategoryResponseDTO} objects matching the partial
     *         name; an empty list if no matches are found
     */

    List<CategoryResponseDTO> searchByPartialName(String name);

    /**
     * Updates the details of an existing category identified by its ID.
     *
     * @param id  the ID of the category to update
     * @param dto the {@link UpdateCategoryDTO} containing the updated data
     * @return the updated category as a {@link CategoryResponseDTO}
     */

    CategoryResponseDTO updateCategory(Long id, UpdateCategoryDTO dto);

    /**
     * Disables a category by setting its status to {@link Status#INACTIVE}, based
     * on its ID.
     *
     * @param id the ID of the category to disable
     * @return the updated category with status set to {@link Status#INACTIVE},
     *         wrapped as a {@link CategoryResponseDTO}
     */

    CategoryResponseDTO disableCategory(Long id);

    /**
     * Enables a category by setting its status to {@link Status#ACTIVE}, based on
     * its ID.
     * 
     * @param id the ID of the category to enable
     * @return the updated category with status set to {@link Status#ACTIVE},
     *         wrapped as a {@link CategoryResponseDTO}
     */

    CategoryResponseDTO enableCategory(Long id);

    /**
     * Deletes a category by its ID using a soft delete strategy.
     * 
     * This operation sets the category's status to {@link Status#DELETED} and
     * records the deletion timestamp.
     * This action is irreversible through this method.
     *
     * @param id the ID of the category to delete
     * @return the logically deleted category as a {@link CategoryResponseDTO}
     */

    CategoryResponseDTO deleteCategory(Long id);

    /**
     * Retrieves all categories that match the given {@link Status}.
     * 
     * This method is useful for filtering active, inactive, or deleted categories.
     *
     * @param status the {@link Status} used to filter the categories
     * @return a list of {@link CategoryResponseDTO} matching the specified status
     */

    List<CategoryResponseDTO> getCategoryByStatus(Status status);

    /**
     * Retrieves all categories with pagination and optional filtering.
     * 
     * @param pageable the pagination information
     * @param filter the filter criteria to apply (optional)
     * @return a {@link Page} of {@link CategoryResponseDTO} objects matching the
     *         filter criteria
     */

    Page<CategoryResponseDTO> getAllCategories(Pageable pageable, CategoryFilterDTO filter);

}
