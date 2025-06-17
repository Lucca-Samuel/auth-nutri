package com.auth.auth_nutri.controller;

import com.auth.auth_nutri.config.security.TokenService;
import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.LoginRequest;
import com.auth.auth_nutri.dto.LoginResponse;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.dto.PacienteDTO;
import com.auth.auth_nutri.service.MedicoService;
import com.auth.auth_nutri.service.PacienteService;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import com.auth.auth_nutri.service.responses.PacienteResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private TokenService tokenService;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("/medico/register")
    public ResponseEntity<MedicoResponse> medicoRegister(@RequestBody @Valid MedicoDTO data){
        MedicoResponse response = this.medicoService.createMedico(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/paciente/register")
    public ResponseEntity<PacienteResponse> pacienteRegister(@RequestBody @Valid PacienteDTO data){
        PacienteResponse response = this.pacienteService.createPaciente(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/medico/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest body){
        LoginResponse response = medicoService.medicoLogin(body);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
