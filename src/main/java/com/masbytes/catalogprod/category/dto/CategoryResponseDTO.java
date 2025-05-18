package com.masbytes.catalogprod.category.dto;

import java.time.LocalDateTime;

public class CategoryResponseDTO {

    /**
     * ID of the category
     */
    private Long id;

    /**
     * Name of the category
     */
    private String name;

    /**
     * Description of the category
     */
    private String description;

    /**
     * Created date of the category
     */
    private LocalDateTime createdAt;

    /**
     * Last updated date of the category
     */
    private LocalDateTime updatedAt;

    /**
     * Deleted date of the category
     */
    private LocalDateTime deletedAt;

    /**
     * Status of the category
     * Possible values: ACTIVE, INACTIVE, DELETED
     */
    private String status;

    // No-args constructor
    public CategoryResponseDTO() {

    }

    // Constructor with parameters
    public CategoryResponseDTO(Long id, String name, String description, LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    // Getters

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return LocalDateTime return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @return LocalDateTime return the updatedAt 
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return LocalDateTime return the deletedAt
     */
    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "CategoryResponseDTO [id=" + id + ", name=" + name + ", description=" + description + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + ", status=" + status + "]";
    }

}
