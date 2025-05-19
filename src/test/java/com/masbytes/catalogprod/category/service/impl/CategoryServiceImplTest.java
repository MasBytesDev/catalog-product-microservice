package com.masbytes.catalogprod.category.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.CategoryResponseDTO;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyExistsException;
import com.masbytes.catalogprod.category.exception.validation.CategoryInvalidDataException;
import com.masbytes.catalogprod.category.mapper.CategoryMapper;
import com.masbytes.catalogprod.category.model.Category;
import com.masbytes.catalogprod.category.repository.CategoryRepository;
import com.masbytes.catalogprod.enums.Status;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void shouldCreateCategorySuccessfullyWhenDataIsValid() {
        // Arrange
        CategoryRequestDTO dto = new CategoryRequestDTO("ELECTRÓNICA", "PRODUCTOS ELECTRÓNICOS");
        Category category = new Category();
        category.setId(1L);
        category.setName("ELECTRÓNICA");
        category.setDescription("PRODUCTOS ELECTRÓNICOS");
        category.setStatus(Status.ACTIVE);

        CategoryResponseDTO expectedResponse = new CategoryResponseDTO(
                1L, "ELECTRÓNICA", "PRODUCTOS ELECTRÓNICOS", null, null, null, Status.ACTIVE);

        when(categoryRepository.existsByName("ELECTRÓNICA")).thenReturn(false);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        // Act
        CategoryResponseDTO result = categoryService.createCategory(dto);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getName(), result.getName());
        assertEquals(expectedResponse.getDescription(), result.getDescription());
        assertEquals(expectedResponse.getStatus(), result.getStatus());

        verify(categoryRepository).existsByName("ELECTRÓNICA");
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void shouldThrowExceptionWhenCategoryRequestIsNull() {
        // Arrange
        CategoryRequestDTO dto = null;

        // Act & Assert
        CategoryInvalidDataException exception = assertThrows(CategoryInvalidDataException.class, () -> {
            categoryService.createCategory(dto);
        });

        assertEquals("Category data cannot be null", exception.getMessage());
        verifyNoInteractions(categoryRepository);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameIsNullOrEmpty() {
        // Name null or empty
        CategoryRequestDTO dtoEmpty = new CategoryRequestDTO("  ", "PRODUCTOS DE LIMPIEZA");

        CategoryInvalidDataException exceptionEmpty = assertThrows(CategoryInvalidDataException.class, () -> {
            categoryService.createCategory(dtoEmpty);
        });
        assertEquals("Category name cannot be null or empty", exceptionEmpty.getMessage());

        // name null
        CategoryRequestDTO dtoNull = new CategoryRequestDTO(null, "PRODUCTOS DE LIMPIEZA");

        CategoryInvalidDataException exceptionNull = assertThrows(CategoryInvalidDataException.class, () -> {
            categoryService.createCategory(dtoNull);
        });
        assertEquals("Category name cannot be null or empty", exceptionNull.getMessage());

        verifyNoInteractions(categoryRepository);
    }

    @Test
    void shouldThrowExceptionWhenCategoryDescriptionIsNullOrEmpty() {
        // description empty
        CategoryRequestDTO dtoEmpty = new CategoryRequestDTO("HOGAR", "  ");

        CategoryInvalidDataException exceptionEmpty = assertThrows(CategoryInvalidDataException.class, () -> {
            categoryService.createCategory(dtoEmpty);
        });
        assertEquals("Category description cannot be null or empty", exceptionEmpty.getMessage());

        // description null
        CategoryRequestDTO dtoNull = new CategoryRequestDTO("HOGAR", null);

        CategoryInvalidDataException exceptionNull = assertThrows(CategoryInvalidDataException.class, () -> {
            categoryService.createCategory(dtoNull);
        });
        assertEquals("Category description cannot be null or empty", exceptionNull.getMessage());

        verifyNoInteractions(categoryRepository);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameAlreadyExists() {
        // Arrange
        CategoryRequestDTO dto = new CategoryRequestDTO("HIGIENE", "PRODUCTOS DE HIGIENE PERSONAL");

        // Simulamos que ya existe una categoría con ese nombre
        when(categoryRepository.existsByName("HIGIENE")).thenReturn(true);

        // Act & Assert
        CategoryAlreadyExistsException exception = assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.createCategory(dto);
        });

        assertEquals("Category with name HIGIENE already exists", exception.getMessage());

        verify(categoryRepository).existsByName("HIGIENE");
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    void shouldThrowExceptionWhenCategoryDescriptionIsOmitted() {
        // Arrange: DTO sin descripción
        CategoryRequestDTO dto = new CategoryRequestDTO("ELECTRODOMESTICOS", null);

        // Act & Assert
        CategoryInvalidDataException exception = assertThrows(CategoryInvalidDataException.class, () -> {
            categoryService.createCategory(dto);
        });

        assertEquals("Category description cannot be null or empty", exception.getMessage());

        verifyNoInteractions(categoryRepository);
    }

}
