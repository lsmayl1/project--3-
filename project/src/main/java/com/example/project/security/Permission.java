package com.example.project.security;

public enum Permission {
    READ("site read"),
    WRITE("site write");

    private final String permissionInfo;

    Permission(String permissionInfo) {
        this.permissionInfo = permissionInfo;
    }

    public String getPermissionInfo() {
        return permissionInfo;
    }
}
