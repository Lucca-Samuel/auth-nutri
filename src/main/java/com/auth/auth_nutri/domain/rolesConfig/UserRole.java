package com.auth.auth_nutri.domain.rolesConfig;

public enum UserRole {
    ADMIN("admin"),
    PACIENTE("paciente"),
    MEDICO("medico");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
