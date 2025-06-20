package com.auth.auth_nutri.domain;

import com.auth.auth_nutri.domain.rolesConfig.UserRole;
import com.auth.auth_nutri.dto.MedicoUpdateDTO;
import com.auth.auth_nutri.service.responses.MedicoUpdateResponseAndRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicos_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico extends User{
    @Column(unique = true)
    @NotNull
    @Size(max = 10)
    private String CRN;

    private String especialidade;

    /**public Medico(MedicoDTO data) {
     this.setNome(data.nome());
     this.setSobrenome(data.sobrenome());
     this.setTelefone(data.telefone());
     this.setEmail(data.email());
     this.setEmailRecovery(data.emailRecovery());
     this.setSenha(data.senha());
     this.setEstado(data.estado());
     this.setCidade(data.cidade());
     this.setBairro(data.bairro());
     this.setLogradouro(data.logradouro());
     this.setNumCasa(data.numCasa());
     this.setCep(data.cep());
     this.setCRN(data.CRN());
     this.setEspecialidades(data.especialidadesID());
     }**/
    public Medico(String nome, String sobrenome, String telefone, String email, String emailRecovery, String senha, String estado, String cidade, String bairro, String logradouro, String numCasa, String cep, String CRN, String especialidade){
        this.setNome(nome);
        this.setSobrenome(sobrenome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setEmailRecovery(emailRecovery);
        this.setSenha(senha);
        this.setEstado(estado);
        this.setCidade(cidade);
        this.setBairro(bairro);
        this.setLogradouro(logradouro);
        this.setNumCasa(numCasa);
        this.setCep(cep);
        this.setCRN(CRN);
        this.setEspecialidade(especialidade);
        this.setRole(UserRole.MEDICO);
    }

    public void medicoUpdateFromDTO(MedicoUpdateResponseAndRequest data){
        if (data.nome() != null) this.setNome(data.nome());
        if (data.sobrenome() != null) this.setSobrenome(data.sobrenome());
        if (data.telefone() != null) this.setTelefone(data.telefone());
        if (data.emailRecovery() != null) this.setEmailRecovery(data.emailRecovery());
        if (data.estado() != null) this.setEstado(data.estado());
        if (data.cidade() != null) this.setCidade(data.cidade());
        if (data.bairro() != null) this.setBairro(data.bairro());
        if (data.logradouro() != null) this.setLogradouro(data.logradouro());
        if (data.numCasa() != null) this.setNumCasa(data.numCasa());
        if (data.cep() != null) this.setCep(data.cep());
    }
}
