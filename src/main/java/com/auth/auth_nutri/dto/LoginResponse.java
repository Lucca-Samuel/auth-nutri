package com.auth.auth_nutri.dto;

import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.domain.Paciente;

public record LoginResponse(String email, String token, String id) {

    public static LoginResponse from(Medico medico, String token){
        return new LoginResponse(
                medico.getEmail(),
                token,
                medico.getId()
        );
    }

    public static LoginResponse from(Paciente paciente, String token){
        return new LoginResponse(
                paciente.getEmail(),
                token,
                paciente.getId()
        );
    }
}
