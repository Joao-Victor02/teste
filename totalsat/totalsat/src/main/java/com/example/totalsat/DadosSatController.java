package com.example.totalsat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DadosSatController {

    @CrossOrigin(origins = "http://127.0.0.1:5500/totalsat/src/main/resources/templates/teste1.html") 
    @GetMapping("http://localhost:8080/dadosSat")
    public String getDadosSat() {
        
        return "Dados do Satélite";
    }
}