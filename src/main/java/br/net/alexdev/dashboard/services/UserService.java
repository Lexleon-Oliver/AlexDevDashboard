package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.dtos.responses.UserDto;
import br.net.alexdev.dashboard.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> getLoggedUser(HttpServletRequest request);
}
