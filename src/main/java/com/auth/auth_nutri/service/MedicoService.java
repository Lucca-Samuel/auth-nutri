package com.auth.auth_nutri.service;

import com.auth.auth_nutri.config.security.TokenService;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.LoginRequest;
import com.auth.auth_nutri.dto.LoginResponse;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.exceptions.SenhaIncorretaException;
import com.auth.auth_nutri.repository.MedicoRepository;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import com.auth.auth_nutri.service.responses.MedicoUpdateResponseAndRequest;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    /**
     *Poderia ter feito uma classe de serviço para USER diretamente, utilizando de metodos abstratos, apenas os sobreescrevendo nas Services de Med & Pac.
     * meyodos comuns como:
     * findAll()
     * findById(),
     * Pagagination(),
     * delete()
     * findByEmail(),
     */


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

    public List<MedicoResponse> findPagination(int pagina, int itens) {
        Page<Medico> medicos = this.repository.findAll(PageRequest.of(pagina, itens));
        return medicos.stream()
                .map(medico -> MedicoResponse.from(medico, null))
                .toList();
    }

    public MedicoUpdateResponseAndRequest findById(String id) throws NoResultException{
        Medico medico = this.repository.findById(id)
                .orElseThrow(() -> new NoResultException("Médico não encontrado"));

        return MedicoUpdateResponseAndRequest.from(medico);
       //return this.repository.findById(id).orElseThrow(() -> new NoResultException("Medico não encontrado"));
    }

    public Medico findByEmail(String email){
        return this.repository.findByEmail(email).orElseThrow(() -> new NoResultException("Médico não encontrado"));
    }

    public MedicoUpdateResponseAndRequest updateMedico(String id, MedicoUpdateResponseAndRequest request){
        Medico medico = this.internalFindById(id);

        medico.medicoUpdateFromDTO(request);

        this.save(medico);

        return MedicoUpdateResponseAndRequest.from(medico);
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

    public String emailToId(String email){
        Medico medico = this.findByEmail(email);
        String id = medico.getId();
        System.out.println("Id do service: " + id);
        return id;
    }

    /**
     * @param id
     * @return
     * @throws NoResultException
     * Use only in internal ecossystem
     */
    public Medico internalFindById(String id) throws NoResultException{
        return this.repository.findById(id).orElseThrow(() -> new NoResultException("Medico não encontrado"));
    }



    public void save(Medico medico){
        this.repository.save(medico);
    }
}
