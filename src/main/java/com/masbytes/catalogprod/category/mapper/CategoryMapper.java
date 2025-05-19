package com.masbytes.catalogprod.category.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.CategoryResponseDTO;
import com.masbytes.catalogprod.category.dto.UpdateCategoryDTO;
import com.masbytes.catalogprod.category.model.Category;
import com.masbytes.catalogprod.enums.Status;

public class CategoryMapper {

    /**
     * Converts a CategoryRequestDTO to a Category entity.
     *
     * @param dto The DTO to convert.
     * @return A new Category entity, or null if the input is null.
     */

    public static Category toEntity(CategoryRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Category category = new Category();
        category.setName(dto.getName().trim().toUpperCase(Locale.ROOT));
        category.setDescription(dto.getDescription().trim().toUpperCase(Locale.ROOT));
        category.setStatus(Status.ACTIVE);
        return category;
    }

    /**
     * Convert an entity to a ResponseDTO.
     * 
     * @param entity to convert.
     * @return a new ResponseDTO.
     */

    public static CategoryResponseDTO toResponseDTO(Category entity) {
        if (entity == null) {
            return null;
        }

        return new CategoryResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt(),
                entity.getStatus());
    }

    /**
     * Updates a Category entity using the values provided in an UpdateCategoryDTO.
     *
     * Fields with null or blank values will be ignored.
     *
     * @param entity The Category entity to update.
     * @param dto    The DTO containing updated values.
     */

    public static void updateEntity(Category entity, UpdateCategoryDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName().trim().toUpperCase());
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            entity.setDescription(dto.getDescription().trim().toUpperCase());
        }

        // Solo actualizamos status si no es DELETED
        if (dto.getStatus() != null && dto.getStatus() != Status.DELETED) {
            entity.setStatus(dto.getStatus());
        }
    }

    /**
     * Convert a list of entities to a list of ResponseDTOs.
     * 
     * @param categories to convert.
     * @return a new list of ResponseDTOs.
     *         If the list is null or empty, an empty list is returned.
     */
    
    public static List<CategoryResponseDTO> toResponseDTOList(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            return Collections.emptyList();
        }

        return categories.stream()
                .map(CategoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

}
