package com.auth.auth_nutri.service;

import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.domain.enums.SexoEnum;
import com.auth.auth_nutri.dto.PacienteDTO;
import com.auth.auth_nutri.repository.PacienteRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository repository;

    public Paciente createPaciente(PacienteDTO data){

        if(data.sexo() != SexoEnum.MASCULINO && data.sexo() != SexoEnum.FEMININO){
            throw new IllegalArgumentException("Sexo inválido: apenas MASCULINO ou FEMININO são permitidos.");
        }

        Paciente newPaciente = new Paciente(data);
        this.save(newPaciente);
        return newPaciente;
    }

    public List<Paciente> getAllPaciente(){
        return this.repository.findAll();
    }

    public Paciente findPacienteById(String id) throws NoResultException{
        return this.repository.findById(id)
                .orElseThrow(() -> new NoResultException("Usuário não encontrado"));
    }


    public void save(Paciente paciente){
        this.repository.save(paciente);
    }
}
