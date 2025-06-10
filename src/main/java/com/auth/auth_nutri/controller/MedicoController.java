package com.auth.auth_nutri.controller;

import com.auth.auth_nutri.config.security.TokenService;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.repository.UserRepository;
import com.auth.auth_nutri.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Medico> register(@RequestBody @Valid MedicoDTO data){
        Medico newMedico = this.service.createMedico(data);
        return new ResponseEntity<>(newMedico, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medico>> findall(){
       List<Medico> medicos = this.service.findAll();
       return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/medicoId")
    public Medico findMedicoById(@RequestParam String id){
        Medico medico = this.service.findById(id);
        return medico;
    }
}
