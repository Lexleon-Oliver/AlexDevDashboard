package br.net.alexdev.dashboard.controllers;

import br.net.alexdev.dashboard.dtos.requests.ThemeDto;
import br.net.alexdev.dashboard.dtos.responses.MessageResponse;
import br.net.alexdev.dashboard.dtos.responses.UserDto;
import br.net.alexdev.dashboard.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    UserService usuarioService;

    @GetMapping("/mydetails")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('SUPPORT')")
    public ResponseEntity<UserDto> getLoggedUser(HttpServletRequest request){
        return usuarioService.getLoggedUser(request);
    }

    @PutMapping("/mydetails/themes")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('SUPPORT')")
    public ResponseEntity<MessageResponse> updateTheme(HttpServletRequest request, @RequestBody @Valid ThemeDto newTheme){
        return usuarioService.updateTheme(request, newTheme);
    }
}
