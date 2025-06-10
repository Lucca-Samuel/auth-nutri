package com.auth.auth_nutri.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MedicoUpdateDTO(
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

        String especialidade
) {
}
