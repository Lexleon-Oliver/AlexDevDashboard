package br.net.alexdev.dashboard.repositories;

import br.net.alexdev.dashboard.entities.Notification;
import br.net.alexdev.dashboard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
