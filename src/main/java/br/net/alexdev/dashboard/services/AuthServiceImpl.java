package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.dtos.requests.LoginDto;
import br.net.alexdev.dashboard.dtos.requests.Register;
import br.net.alexdev.dashboard.dtos.responses.ErrorMessageResponse;
import br.net.alexdev.dashboard.dtos.responses.JwtResponse;
import br.net.alexdev.dashboard.dtos.responses.MessageResponse;
import br.net.alexdev.dashboard.entities.Role;
import br.net.alexdev.dashboard.entities.User;
import br.net.alexdev.dashboard.enums.ERole;
import br.net.alexdev.dashboard.repositories.RoleRepository;
import br.net.alexdev.dashboard.repositories.UserRepository;
import br.net.alexdev.dashboard.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    private static final Map<String, ERole> ROLE_MAP = Map.of(
            "admin", ERole.ROLE_ADMIN,
            "support", ERole.ROLE_SUPPORT,
            "mod", ERole.ROLE_MODERATOR,
            "user", ERole.ROLE_USER
    );
    @Override
    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginDto login) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.username(), login.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateRefreshToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwt, refreshToken));
        }  catch (BadCredentialsException | DisabledException | LockedException e) {
            throw e; // Rethrow the exception to be handled by the ErrorController
        } catch (Exception e) {
            throw new RuntimeException("Erro de autenticação", e);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> registerUser(Register register) {
        if (userRepository.existsByUsername(register.username())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username já registrado!"));
        }

        if (userRepository.existsByEmail(register.email())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: E-mail já registrado!"));
        }
        String password = "Mudar123@";
        // Create new user's account
        User usuario = new User(register.username(),
                encoder.encode(password));

        usuario.setEmail(register.email());
        usuario.setName(register.name());
        usuario.setJobTitle(register.jobTitle());
        usuario.setCompany("");
        usuario.setAbout("");
        usuario.setAddress("");
        usuario.setCity("");
        usuario.setPhone("");
        usuario.setHasNewNotifications(false);
        usuario.setActive(true);

        Set<Role> roles = defineUserRoles(register.roles());
        usuario.setRoles(roles);
        userRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Usuario criado com sucesso!"));

    }

    private Set<Role> defineUserRoles(Set<String> strRoles) {
        if (strRoles == null) {
            strRoles = Set.of(); // Inicializa como um conjunto vazio
        }
        if (strRoles.isEmpty()) {
            strRoles = Set.of("user"); // Define "user" como a função padrão se o conjunto estiver vazio
        }

        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> roles.add(getRoleFromRepository(ROLE_MAP.get(role))));
        return roles;
    }

    private Role getRoleFromRepository(ERole role) {
        if (role== null){
            role = ERole.ROLE_USER;
        }
        return roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Error: Role não encontrada."));
    }
}
