package com.masbytes.catalogprod.category.dto;

import com.masbytes.catalogprod.enums.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCategoryDTO {

    @NotBlank(message = "NAME field is required")
    @Size(min = 3, max = 50, message = "NAME field must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "DESCRIPTION field is required")
    @Size(min = 5, max = 255, message = "DESCRIPTION field must be between 3 and 255 characters")
    private String description;

    /**
     * Status of the category
     * Possible values: ACTIVE, INACTIVE, DELETED
     * Default value: ACTIVE
     */
    @NotNull(message = "STATUS field is required")
    private Status status;

    // No-args constructor
    public UpdateCategoryDTO() {

    }

    // Constructor with parameters
    public UpdateCategoryDTO(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateCategoryDTO [name=" + name + ", description=" + description + ", status=" + status + "]";
    }

}
