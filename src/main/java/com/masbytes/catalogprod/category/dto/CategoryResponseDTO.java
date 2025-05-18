package com.masbytes.catalogprod.category.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.masbytes.catalogprod.enums.Status;

public class CategoryResponseDTO {

    private Long id;

    private String name;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedAt;

    private Status status;

    // No-args constructor
    public CategoryResponseDTO() {

    }

    // Constructor with parameters
    public CategoryResponseDTO(Long id, String name, String description, LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.status = status;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CategoryResponseDTO [id=" + id + ", name=" + name + ", description=" + description + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + ", status=" + status + "]";
    }

}
