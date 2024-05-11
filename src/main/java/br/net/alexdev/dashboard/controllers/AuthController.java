package br.net.alexdev.dashboard.controllers;

import br.net.alexdev.dashboard.dtos.requests.LoginDto;
import br.net.alexdev.dashboard.dtos.requests.Register;
import br.net.alexdev.dashboard.dtos.responses.JwtResponse;
import br.net.alexdev.dashboard.dtos.responses.MessageResponse;
import br.net.alexdev.dashboard.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginDto login) {
       return authService.authenticateUser( login);
    }

    @PostMapping("/register")
//    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('SUPPORT')")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody Register register) {
        return authService.registerUser(register);
    }
}
