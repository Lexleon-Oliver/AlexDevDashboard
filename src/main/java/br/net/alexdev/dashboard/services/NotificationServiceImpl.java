package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.entities.Notification;
import br.net.alexdev.dashboard.entities.User;
import br.net.alexdev.dashboard.enums.NotificationType;
import br.net.alexdev.dashboard.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void generateNotification(User user, NotificationType type, String title, String message) {

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(type);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setCreatedAt(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now()));
        notification.setReadedAt("");
        notification.setActive(true);

        try {
            // Salvar a notificação
            Notification notificationSaved = notificationRepository.save(notification);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a pilha de exceção no console
            throw e; // Relança a exceção para ser capturada no controlador, se necessário
        }
    }



}
