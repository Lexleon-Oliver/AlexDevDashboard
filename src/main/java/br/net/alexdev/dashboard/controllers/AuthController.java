package br.net.alexdev.dashboard.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<String> authenticateUser(){
        try{
            return ResponseEntity.ok("Authenticated");
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
