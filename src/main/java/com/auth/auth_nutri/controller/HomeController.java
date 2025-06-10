package com.auth.auth_nutri.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class HomeController {
    @GetMapping
    public String testeHome(){
        return "API SUBIU!!!!!!!";
    }
}
