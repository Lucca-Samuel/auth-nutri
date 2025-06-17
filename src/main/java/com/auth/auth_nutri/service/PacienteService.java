package com.auth.auth_nutri.service;

import com.auth.auth_nutri.config.security.TokenService;
import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.domain.enums.SexoEnum;
import com.auth.auth_nutri.dto.PacienteDTO;
import com.auth.auth_nutri.repository.PacienteRepository;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import com.auth.auth_nutri.service.responses.PacienteResponse;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    @Autowired
    private TokenService tokenService;

    public PacienteResponse createPaciente(PacienteDTO data){

        if(data.sexo() != SexoEnum.MASCULINO && data.sexo() != SexoEnum.FEMININO){
            throw new IllegalArgumentException("Sexo inválido: apenas MASCULINO ou FEMININO são permitidos.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        Paciente newPaciente = new Paciente(
                data.nome(),
                data.sobrenome(),
                data.telefone(),
                data.email(),
                data.emailRecovery(),
                encryptedPassword,
                data.estado(),
                data.cidade(),
                data.bairro(),
                data.logradouro(),
                data.numCasa(),
                data.cep(),
                data.peso(),
                data.altura(),
                data.sexo()
        );
        this.save(newPaciente);

        String token = this.tokenService.generateToken(newPaciente);

        return PacienteResponse.from(newPaciente, token);
    }

    public List<PacienteResponse> getAllPaciente(){
        List<Paciente> pacientes = this.repository.findAll();
        return pacientes.stream()
                .map(paciente -> PacienteResponse.from(paciente, null))
                .toList();
    }

    public Paciente findPacienteById(String id) throws NoResultException{
        return this.repository.findById(id)
                .orElseThrow(() -> new NoResultException("Usuário não encontrado"));
    }


    public void save(Paciente paciente){
        this.repository.save(paciente);
    }
}
