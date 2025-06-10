package com.auth.auth_nutri.service;

import com.auth.auth_nutri.config.security.TokenService;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.repository.MedicoRepository;
import com.auth.auth_nutri.repository.UserRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public Medico createMedico(MedicoDTO data){
        if(this.userRepository.findByEmail(data.email()) != null){
            throw new RuntimeException("Medico não encontrado");
        };

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        Medico newMedico = new Medico(
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
                data.CRN(),
                data.especialidade()
        );

        this.save(newMedico);
        return newMedico;
    }

    public List<Medico> findAll(){
       List<Medico> medicos = this.repository.findAll();
       return medicos;
    }

    public Medico findById(String id){
       return this.repository.findById(id).orElseThrow(() -> new NoResultException("Medico não encontrado"));
    }



    public void save(Medico medico){
        this.repository.save(medico);
    }
}
