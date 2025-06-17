package com.auth.auth_nutri.controller;

import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.service.MedicoService;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    @Autowired
    private MedicoService service;

    @GetMapping("/all")
    public ResponseEntity<List<MedicoResponse>> findall(){
       List<MedicoResponse> medicos = this.service.findAll();
       return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/medicoId")
    public Medico findMedicoById(@RequestParam String id){
        Medico medico = this.service.findById(id);
        return medico;
    }
}
