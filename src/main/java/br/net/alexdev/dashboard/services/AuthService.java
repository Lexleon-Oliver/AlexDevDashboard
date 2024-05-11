package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.dtos.requests.LoginDto;
import br.net.alexdev.dashboard.dtos.requests.Register;
import br.net.alexdev.dashboard.dtos.responses.JwtResponse;
import br.net.alexdev.dashboard.dtos.responses.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<MessageResponse> registerUser(Register register);

    ResponseEntity<JwtResponse> authenticateUser(@Valid LoginDto login);
}
