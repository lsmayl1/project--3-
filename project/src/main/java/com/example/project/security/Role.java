package com.example.project.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.project.security.Permission.*;

public enum Role {
    USER(Sets.newHashSet(READ, WRITE));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        return getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionInfo()))
                .collect(Collectors.toSet());
    }
}
