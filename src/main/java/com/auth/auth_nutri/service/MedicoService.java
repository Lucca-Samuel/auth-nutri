package com.auth.auth_nutri.service;

import com.auth.auth_nutri.config.security.TokenService;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.LoginRequest;
import com.auth.auth_nutri.dto.LoginResponse;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.exceptions.SenhaIncorretaException;
import com.auth.auth_nutri.repository.MedicoRepository;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public MedicoResponse createMedico(MedicoDTO data){
//        if(this.repository.findByEmail(data.email()) != null){
//            Medico medicoCadastrado = this.findByEmail(data.email());
//            throw new RuntimeException("Medico já está cadastrado");
//            return MedicoResponse.from(medicoCadastrado, "");
//        };
//        this.isExisting(data.email());

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

        String token = this.tokenService.generateToken(newMedico);

        return MedicoResponse.from(newMedico, token);
    }

    public List<MedicoResponse> findAll() {
        List<Medico> medicos = this.repository.findAll();
        return medicos.stream()
                .map(medico -> MedicoResponse.from(medico, null))
                .toList();
    }

    public Medico findById(String id){
       return this.repository.findById(id).orElseThrow(() -> new NoResultException("Medico não encontrado"));
    }

    public Medico findByEmail(String email){
        return this.repository.findByEmail(email).orElseThrow(() -> new NoResultException("Médico não encontrado"));
    }

    public MedicoResponse isExisting(String email){
        Medico medico = this.findByEmail(email);
        return MedicoResponse.from(medico, "Médico já cadastrado");
    }

    public LoginResponse medicoLogin(LoginRequest request){
        Medico medico = this.findByEmail(request.email());
        if (!passwordEncoder.matches(request.senha(), medico.getSenha())){
            throw new SenhaIncorretaException("Senha incorreta");
        }
        String token = this.tokenService.generateToken(medico);
        return LoginResponse.from(medico, token);
    }



    public void save(Medico medico){
        this.repository.save(medico);
    }
}
