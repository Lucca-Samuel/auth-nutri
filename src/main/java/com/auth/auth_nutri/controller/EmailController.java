package com.auth.auth_nutri.controller;

import com.auth.auth_nutri.dto.EmailDTO;
import com.auth.auth_nutri.service.EmailService;
import com.auth.auth_nutri.service.responses.EmailResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService service;

    @PostMapping("/send")
    public ResponseEntity sendEmail(@RequestBody EmailDTO data){
       EmailResponseDTO emailResponseDTO = this.service.createEmail(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(emailResponseDTO);
    }
}
