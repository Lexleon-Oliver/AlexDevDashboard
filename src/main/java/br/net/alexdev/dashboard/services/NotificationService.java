package br.net.alexdev.dashboard.services;

import br.net.alexdev.dashboard.entities.User;
import br.net.alexdev.dashboard.enums.NotificationType;

public interface NotificationService {

    void generateNotification(User user, NotificationType type, String title, String message);
}
