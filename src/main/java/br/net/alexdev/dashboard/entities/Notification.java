package br.net.alexdev.dashboard.entities;

import br.net.alexdev.dashboard.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_notificacoes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "idNotificacoes")
        })
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 100)
    private String message;

    @NotBlank
    @Size(max = 20)
    private String createdAt;


    @Size(max = 20)
    private String readedAt;

    private boolean isActive;

    public String getType() {
        return type.getType();
    }

    public String getIcon() {
        return type.getIcon();
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user=" + (user!= null? user.toString() : "null") +
                ", type=" + (type!= null? type.getType() : "null") +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", readedAt='" + readedAt + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
