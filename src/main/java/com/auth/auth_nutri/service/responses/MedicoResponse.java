package com.auth.auth_nutri.service.responses;

import com.auth.auth_nutri.domain.Medico;
import jakarta.validation.constraints.NotNull;

public record MedicoResponse(
        String nome,
        String email,
        String CRN,
        String token
) {

    public static MedicoResponse from(Medico medico, String token) {
        return new MedicoResponse(
                medico.getNome(),
                medico.getEmail(),
                medico.getCRN(),
                token
        );
    }

}
