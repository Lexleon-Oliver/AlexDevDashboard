package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.dtos.requests.ThemeDto;
import br.net.alexdev.dashboard.dtos.responses.MessageResponse;
import br.net.alexdev.dashboard.dtos.responses.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> getLoggedUser(HttpServletRequest request);

    ResponseEntity<MessageResponse> updateTheme(HttpServletRequest request, ThemeDto newTheme);
}
