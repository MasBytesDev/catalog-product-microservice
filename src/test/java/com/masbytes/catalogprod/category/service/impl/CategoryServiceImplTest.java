package com.masbytes.catalogprod.category.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import com.masbytes.catalogprod.category.dto.CategoryRequestDTO;
import com.masbytes.catalogprod.category.dto.CategoryResponseDTO;
import com.masbytes.catalogprod.category.exception.database.CategoryAlreadyExistsException;
import com.masbytes.catalogprod.category.exception.database.CategoryNotFoundException;
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

    @Test
    void getCategoryById_shouldThrowException_whenIdIsNull() {
        // Arrange
        Long id = null;

        // Act & Assert
        assertThrows(CategoryInvalidDataException.class, () -> categoryService.getCategoryById(id));
    }

    @Test
    void getCategoryById_shouldThrowException_whenCategoryNotFound() {
        // Arrange
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(id));
    }

    @Test
    void givenValidId_whenCategoryExists_thenReturnsExpectedResponse() {
        // Arrange
        Long id = 1L;

        Category category = new Category();
        category.setId(id);
        category.setName("TECH");
        category.setDescription("TECHNOLOGY");
        category.setStatus(Status.ACTIVE);
        category.setCreatedAt(LocalDateTime.now());

        CategoryResponseDTO expectedResponse = new CategoryResponseDTO(
                id, "TECH", "TECHNOLOGY", category.getCreatedAt(), null, null, Status.ACTIVE);

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        // Simulamos el mapper real, aunque en el proyecto es estatico, igual se puede
        // mockear y modularizarlo mas adelante.
        try (MockedStatic<CategoryMapper> mapperMock = mockStatic(CategoryMapper.class)) {
            mapperMock.when(() -> CategoryMapper.toResponseDTO(category)).thenReturn(expectedResponse);

            // Act
            CategoryResponseDTO actualResponse = categoryService.getCategoryById(id);

            // Assert
            assertNotNull(actualResponse);
            assertEquals(expectedResponse.getId(), actualResponse.getId());
            assertEquals(expectedResponse.getName(), actualResponse.getName());
            assertEquals(expectedResponse.getDescription(), actualResponse.getDescription());
        }
    }

    @Test
    void givenValidId_whenCategoryHasNullFields_thenReturnsPartialData() {
        // Arrange
        Long id = 2L;

        Category category = new Category();
        category.setId(id);
        category.setName("FOOD");
        category.setDescription(null); // intentionally null
        category.setStatus(Status.ACTIVE);

        CategoryResponseDTO expectedResponse = new CategoryResponseDTO(
                id, "FOOD", null, null, null, null, Status.ACTIVE);

        when(categoryRepository.findById(id)).thenReturn(Optional.of(category));
        try (MockedStatic<CategoryMapper> mapperMock = mockStatic(CategoryMapper.class)) {
            mapperMock.when(() -> CategoryMapper.toResponseDTO(category)).thenReturn(expectedResponse);

            // Act
            CategoryResponseDTO actualResponse = categoryService.getCategoryById(id);

            // Assert
            assertNotNull(actualResponse);
            assertEquals("FOOD", actualResponse.getName());
            assertNull(actualResponse.getDescription());
        }
    }

    @Test
    void givenValidName_whenCategoryExists_thenReturnsExpectedResponse() {
        // Arrange
        String name = "tech";
        String normalizedName = name.trim().toUpperCase(Locale.ROOT);

        Category category = new Category();
        category.setId(1L);
        category.setName(normalizedName);
        category.setDescription("TECHNOLOGY");
        category.setStatus(Status.ACTIVE);
        category.setCreatedAt(LocalDateTime.now());

        CategoryResponseDTO expectedResponse = new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt(),
                null,
                null,
                Status.ACTIVE);

        when(categoryRepository.findByName(normalizedName)).thenReturn(Optional.of(category));

        try (MockedStatic<CategoryMapper> mapperMock = mockStatic(CategoryMapper.class)) {
            mapperMock.when(() -> CategoryMapper.toResponseDTO(category)).thenReturn(expectedResponse);

            // Act
            CategoryResponseDTO actualResponse = categoryService.getCategoryByName(name);

            // Assert
            assertNotNull(actualResponse);
            assertEquals(expectedResponse.getId(), actualResponse.getId());
            assertEquals(expectedResponse.getName(), actualResponse.getName());
            assertEquals(expectedResponse.getDescription(), actualResponse.getDescription());
        }
    }

    @Test
    void givenNullName_whenGetCategory_thenThrowsCategoryInvalidDataException() {
        // Arrange
        String name = null;

        // Act & Assert
        assertThrows(CategoryInvalidDataException.class, () -> categoryService.getCategoryByName(name));
    }

    @Test
    void givenBlankName_whenGetCategory_thenThrowsCategoryInvalidDataException() {
        // Arrange
        String name = "  ";

        // Act & Assert
        assertThrows(CategoryInvalidDataException.class, () -> categoryService.getCategoryByName(name));
    }

    @Test
    void givenValidName_whenCategoryNotExists_thenThrowsCategoryNotFoundException() {
        // Arrange
        String name = "gaming";
        String normalizedName = name.trim().toUpperCase(Locale.ROOT);

        when(categoryRepository.findByName(normalizedName)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryByName(name));
    }

    @Test
    void whenNameIsNull_thenThrowsCategoryInvalidDataException() {
        assertThrows(CategoryInvalidDataException.class,
                () -> categoryService.searchByPartialName(null));
    }

    @Test
    void whenNameIsEmpty_thenThrowsCategoryInvalidDataException() {
        assertThrows(CategoryInvalidDataException.class,
                () -> categoryService.searchByPartialName("   "));
    }

    @Test
    void whenNoCategoriesFound_thenThrowsCategoryNotFoundException() {
        String partialName = "Electronics";

        when(categoryRepository.findByNameContainingIgnoreCase(partialName.trim()))
                .thenReturn(Collections.emptyList());

        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.searchByPartialName(partialName));

        verify(categoryRepository).findByNameContainingIgnoreCase(partialName.trim());
    }

    @Test
    void whenCategoriesFound_thenReturnsListOfDTOs() {
        String partialName = "Electronics";

        LocalDateTime now = LocalDateTime.now();

        Category cat1 = new Category(1L, "Electronics", "Category of electronic devices");
        Category cat2 = new Category(2L, "Electronic Gadgets", "Category of electronic gadgets and accessories");

        List<Category> categories = List.of(cat1, cat2);

        CategoryResponseDTO dto1 = new CategoryResponseDTO(
                1L,
                "Electronics",
                "Category of electronic devices",
                now.minusDays(5),
                now.minusDays(1),
                null,
                Status.ACTIVE);

        CategoryResponseDTO dto2 = new CategoryResponseDTO(
                2L,
                "Electronic Gadgets",
                "Category of electronic gadgets and accessories",
                now.minusDays(10),
                now.minusDays(2),
                null,
                Status.ACTIVE);

        when(categoryRepository.findByNameContainingIgnoreCase(partialName.trim()))
                .thenReturn(categories);

        try (MockedStatic<CategoryMapper> mapperMock = mockStatic(CategoryMapper.class)) {
            mapperMock.when(() -> CategoryMapper.toResponseDTO(cat1)).thenReturn(dto1);
            mapperMock.when(() -> CategoryMapper.toResponseDTO(cat2)).thenReturn(dto2);

            List<CategoryResponseDTO> result = categoryService.searchByPartialName(partialName);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertTrue(result.contains(dto1));
            assertTrue(result.contains(dto2));

            verify(categoryRepository).findByNameContainingIgnoreCase(partialName.trim());
            // No se puede verificar métodos estáticos con verify(), se verifica
            // indirectamente por comportamiento
        }
    }

}
