package com.auth.auth_nutri.controller;

import com.auth.auth_nutri.domain.Paciente;
import com.auth.auth_nutri.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> findAll(){
        List<Paciente> medicos = this.service.getAllPaciente();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/pacienteId")
    public ResponseEntity<Paciente> findById(@RequestParam String id){
        Paciente paciente = this.service.findPacienteById(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }
}
