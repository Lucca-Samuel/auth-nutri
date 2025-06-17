package com.auth.auth_nutri.dto;

public record LoginRequest(
        String email,
        String senha
) {
}
