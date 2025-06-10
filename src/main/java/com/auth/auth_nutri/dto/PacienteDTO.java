package com.auth.auth_nutri.dto;

import com.auth.auth_nutri.domain.enums.SexoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PacienteDTO(
        @NotNull
        @Size(max = 50)
        String nome,

        @NotNull
        @Size(max = 50)
        String sobrenome,

        @NotNull
        @Size(max = 20)
        String telefone,

        @NotNull
        @Email
        @Size(max = 155)
        String email,

        @Email
        @Size(max = 155)
        String emailRecovery,

        @NotNull
        @Size(max = 255)
        String senha,

        @NotNull
        @Size(max = 155)
        String estado,

        @NotNull
        @Size(max = 155)
        String cidade,

        @Size(max = 155)
        String bairro,

        @Size(max = 155)
        String logradouro,

        @Size(max = 10)
        String numCasa,

        @Size(max = 8)
        String cep,

        @Size(max = 10)
        Double peso,

        @Size(max = 10)
        Double altura,

        @Size(max = 10)
        SexoEnum sexo
) {
}
