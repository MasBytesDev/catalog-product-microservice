package com.masbytes.catalogprod.enums;

import java.util.Arrays;

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

    public static Status fromString(String value) {
        for (Status status : Status.values()) {
            if (status.status.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }

    public static boolean isValid(String value) {
        return Arrays.stream(Status.values())
                .anyMatch(status -> status.status.equalsIgnoreCase(value));
    }

}
