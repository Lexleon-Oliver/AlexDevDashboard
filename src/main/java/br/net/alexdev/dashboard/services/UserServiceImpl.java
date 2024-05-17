package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.dtos.responses.NotificationDto;
import br.net.alexdev.dashboard.dtos.responses.UserDto;
import br.net.alexdev.dashboard.entities.Notification;
import br.net.alexdev.dashboard.entities.Role;
import br.net.alexdev.dashboard.entities.User;
import br.net.alexdev.dashboard.exceptions.NotFoundException;
import br.net.alexdev.dashboard.repositories.NotificationRepository;
import br.net.alexdev.dashboard.repositories.UserRepository;
import br.net.alexdev.dashboard.security.jwt.AuthTokenFilter;
import br.net.alexdev.dashboard.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    AuthTokenFilter tokenFilter;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public ResponseEntity<UserDto> getLoggedUser(HttpServletRequest request) {
        var userFound = getUserModelByRequest(request);
        Set<Role> roles = userFound.getRoles();
        Set<String> rolesString = new HashSet<>();
        for (Role role : roles) {
            String roleName = String.valueOf(role.getName());
            rolesString.add(roleName);
        }

        List<NotificationDto> myNotifications = getMyNotifications(request);
        UserDto userToReturn = new UserDto(
                userFound.getId(),
                userFound.getUsername(),
                userFound.getEmail(),
                userFound.getName(),
                userFound.getCompany(),
                userFound.getJobTitle(),
                userFound.getAbout(),
                userFound.getAddress(),
                userFound.getCity(),
                userFound.getPhone(),
                userFound.isHasNewNotifications(),
                userFound.getTheme(),
                rolesString,
                myNotifications
        );
        return ResponseEntity.status(HttpStatus.OK).body(userToReturn);
    }


    public User getUserModelByRequest(HttpServletRequest request) {
        String jwt = tokenFilter.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        var returnedUser =  userRepository.findByUsername(username);
        if (returnedUser.isEmpty()) {
            throw new NotFoundException("Usuário informado não existe no banco de dados!");
        }
        return returnedUser.get();
    }

    public List<NotificationDto> getMyNotifications(HttpServletRequest request) {
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        User loggedUser = getUserModelByRequest(request);
        List<Notification> myNotifications = notificationRepository.findByUser(loggedUser);
        for (Notification notification : myNotifications) {
            NotificationDto newNotification = new NotificationDto(
                    notification.getId(),
                    notification.getType().toUpperCase(),
                    notification.getTitle(),
                    notification.getMessage(),
                    notification.getCreatedAt(),
                    notification.getIcon(),
                    notification.getReadedAt()
                    );
            notificationDtoList.add(newNotification);

        }

        return notificationDtoList;
    }
}
