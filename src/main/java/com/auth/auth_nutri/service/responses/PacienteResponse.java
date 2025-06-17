package com.auth.auth_nutri.service.responses;

import com.auth.auth_nutri.domain.Paciente;

public record PacienteResponse(
        String nome,
        String email,
        String telefone,
        String cep,
        String token
) {

    public static PacienteResponse from(Paciente paciente, String token){
        return new PacienteResponse(
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCep(),
                token
        );
    }
}
