package com.masbytes.catalogprod.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCategoryDTO {

    /**
     * Name of the category
     */
    @NotBlank(message = "NAME field is required")
    @Size(min = 3, max = 50, message = "NAME field must be between 3 and 50 characters")
    private String name;

    /**
     * Description of the category
     */
    @NotBlank(message = "DESCRIPTION field is required")
    @Size(min = 5, max = 255, message = "DESCRIPTION field must be between 3 and 255 characters")
    private String description;

    /**
     * Status of the category
     * Possible values: ACTIVE, INACTIVE, DELETED
     * Default value: ACTIVE
     */
    @NotNull(message = "STATUS field is required")
    private String status;

    // No-args constructor
    public UpdateCategoryDTO() {

    }

    // Constructor with parameters
    public UpdateCategoryDTO(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return "UpdateCategoryDTO [name=" + name + ", description=" + description + ", status=" + status + "]";
    }

}
