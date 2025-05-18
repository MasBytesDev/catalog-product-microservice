package com.masbytes.catalogprod.enums;

public enum Status {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }

}
