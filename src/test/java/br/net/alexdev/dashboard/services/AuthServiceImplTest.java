package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.dtos.requests.Register;
import br.net.alexdev.dashboard.dtos.responses.MessageResponse;
import br.net.alexdev.dashboard.entities.Role;
import br.net.alexdev.dashboard.entities.User;
import br.net.alexdev.dashboard.enums.ERole;
import br.net.alexdev.dashboard.repositories.RoleRepository;
import br.net.alexdev.dashboard.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private AuthServiceImpl userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_UsernameExists() {
        String username = "test.user";
        when(userRepository.existsByUsername(username)).thenReturn(true);
        Set<String> roles = Collections.emptySet();
        Register register = new Register(username, roles, "test@example.com", "Test User", "Software Engineer");
        ResponseEntity<MessageResponse> response = userService.registerUser(register);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Username já registrado!", response.getBody().message());
    }

    @Test
    public void testRegisterUser_EmailExists() {
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);
        Set<String> roles = Collections.emptySet();
        Register register = new Register("test.user", roles, email, "Test User", "Software Engineer");
        ResponseEntity<MessageResponse> response = userService.registerUser(register);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: E-mail já registrado!", response.getBody().message());
    }

    @Test
    public void testRegisterUser_Success() {
        String username = "test.user";
        String email = "test@example.com";
        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(encoder.encode(anyString())).thenReturn("encodedPassword");
        Role userRole = new Role();
        userRole.setName(ERole.ROLE_USER);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(java.util.Optional.of(userRole));
        Set<String> roles = new HashSet<>();
        roles.add(userRole.getName().toString());
        Register register = new Register(username, null, email, "Test User", "Software Engineer");
        ResponseEntity<MessageResponse> response = userService.registerUser(register);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuario criado com sucesso!", response.getBody().message());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testDefineUserRoles_NullRoles() {
        Register register = new Register("test.user", Collections.singleton("mod"), "test@example.com", "Test User", "Software Engineer");
        ResponseEntity<MessageResponse> response = userService.registerUser(register);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuario criado com sucesso!", response.getBody().message());
        verify(userRepository).save(any(User.class));
    }



}