package br.net.alexdev.dashboard.controllers;

import br.net.alexdev.dashboard.dtos.responses.UserDto;
import br.net.alexdev.dashboard.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;

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
}
