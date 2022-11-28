package com.binotify.types;

public enum StatusEnum {
    REJECTED("REJECTED"),
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED");

    private String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
