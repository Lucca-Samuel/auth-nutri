package com.auth.auth_nutri.controller;

import com.auth.auth_nutri.domain.Medico;
import com.auth.auth_nutri.dto.MedicoDTO;
import com.auth.auth_nutri.service.MedicoService;
import com.auth.auth_nutri.service.responses.MedicoResponse;
import com.auth.auth_nutri.service.responses.MedicoUpdateResponseAndRequest;
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

    @GetMapping("/pagination")
    public ResponseEntity<List<MedicoResponse>> findPagination(@RequestParam int pagina, @RequestParam int itens){
        return ResponseEntity.ok(service.findPagination(pagina, itens));
    }

    @GetMapping("/medicoId")
    public ResponseEntity<MedicoUpdateResponseAndRequest> findMedicoById(@RequestParam String id){

        MedicoUpdateResponseAndRequest responseAndRequest = this.service.findById(id);
        return ResponseEntity.ok(responseAndRequest);


        //Medico medico = this.service.findById(id);
        //return ResponseEntity.status(HttpStatus.OK).body(medico);
    }

    @GetMapping("/emailToId")
    public ResponseEntity<String> emailToId(@RequestParam String email){
        String id = this.service.emailToId(email);
        System.out.println("Id do controller: " + id);
        return ResponseEntity.ok(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<MedicoUpdateResponseAndRequest> updateMedico(@RequestParam String id, @RequestBody MedicoUpdateResponseAndRequest request){
        MedicoUpdateResponseAndRequest responseAndRequest = this.service.updateMedico(id, request);
        return ResponseEntity.ok(responseAndRequest);
    }



}
