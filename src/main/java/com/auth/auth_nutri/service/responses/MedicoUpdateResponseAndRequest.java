package com.auth.auth_nutri.service.responses;

import com.auth.auth_nutri.domain.Medico;

public record MedicoUpdateResponseAndRequest(
        String nome,
        String sobrenome,
        String telefone,
        String emailRecovery,
        String estado,
        String cidade,
        String bairro,
        String logradouro,
        String numCasa,
        String cep
) {

    public static MedicoUpdateResponseAndRequest from(Medico medico){
        return new MedicoUpdateResponseAndRequest(
                medico.getNome(),
                medico.getSobrenome(),
                medico.getTelefone(),
                medico.getEmailRecovery(),
                medico.getEstado(),
                medico.getCidade(),
                medico.getBairro(),
                medico.getLogradouro(),
                medico.getNumCasa(),
                medico.getCep()
        );

    }
}
