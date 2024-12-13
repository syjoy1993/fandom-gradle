package com.team5.fandom.entity.value;

import java.util.Arrays;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static Role getInstance(String roleType) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getRoleName().equals(roleType))
                .findFirst()
                .orElseThrow();
    }
}
