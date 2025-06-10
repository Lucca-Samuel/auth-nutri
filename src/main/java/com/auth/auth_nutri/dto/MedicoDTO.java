package com.auth.auth_nutri.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record MedicoDTO(
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

        @Column(unique = true)
        @NotNull
        @Size(max = 10)
        String CRN,

        String especialidade
) {
}
