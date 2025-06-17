package com.auth.auth_nutri.domain;

import com.auth.auth_nutri.domain.enums.SexoEnum;
import com.auth.auth_nutri.domain.rolesConfig.UserRole;
import com.auth.auth_nutri.dto.PacienteDTO;
import com.auth.auth_nutri.dto.PacienteUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pacientes_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente extends User{
    @Size(max = 10)
    private Double peso;
    @Size(max = 10)
    private Double altura;
    @Size(max = 10)
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    public Paciente(String nome, String sobrenome, String telefone, String email, String emailRecovery, String senha, String estado, String cidade, String bairro, String logradouro, String numCasa, String cep, Double peso, Double altura, SexoEnum sexo){
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
        this.setPeso(peso);
        this.setAltura(altura);
        this.setSexo(sexo);
        this.setRole(UserRole.PACIENTE);
    }


    public void updateFromDTO(PacienteUpdateDTO data) {
        if (data.nome() != null) this.setNome(data.nome());
        if (data.sobrenome() != null) this.setSobrenome(data.sobrenome());
        if (data.telefone() != null) this.setTelefone(data.telefone());
        if (data.email() != null) this.setEmail(data.email());
        if (data.emailRecovery() != null) this.setEmailRecovery(data.emailRecovery());
        if (data.estado() != null) this.setEstado(data.estado());
        if (data.cidade() != null) this.setCidade(data.cidade());
        if (data.bairro() != null) this.setBairro(data.bairro());
        if (data.logradouro() != null) this.setLogradouro(data.logradouro());
        if (data.numCasa() != null) this.setNumCasa(data.numCasa());
        if (data.cep() != null) this.setCep(data.cep());
        if (data.peso() != null) this.setPeso(data.peso());
        if (data.altura() != null) this.setAltura(data.altura());
        if (data.sexo() != null) this.setSexo(data.sexo());
    }
}
