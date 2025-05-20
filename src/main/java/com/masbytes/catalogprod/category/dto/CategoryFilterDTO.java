package com.masbytes.catalogprod.category.dto;

import java.time.LocalDateTime;

import com.masbytes.catalogprod.enums.Status;

/**
 * DTO for filtering categories.
 */
public class CategoryFilterDTO {

    private String name;
    private Status status;
    private LocalDateTime createdAfter;
    private LocalDateTime createdBefore;

    // No-args constructor
    public CategoryFilterDTO() {
    }

    // All-args constructor
    public CategoryFilterDTO(String name, Status status, LocalDateTime createdAfter, LocalDateTime createdBefore) {
        this.name = name;
        this.status = status;
        this.createdAfter = createdAfter;
        this.createdBefore = createdBefore;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(LocalDateTime createdAfter) {
        this.createdAfter = createdAfter;
    }

    public LocalDateTime getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(LocalDateTime createdBefore) {
        this.createdBefore = createdBefore;
    }

}
