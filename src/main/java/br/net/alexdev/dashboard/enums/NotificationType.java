package br.net.alexdev.dashboard.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    WARNING("WARNING", "bi bi-exclamation-circle text-warning"),
    DANGER("DANGER", "bi bi-x-circle text-danger"),
    SUCCESS("SUCCESS", "bi bi-check-circle text-success"),
    PRIMARY("PRIMARY", "bi bi-info-circle text-primary");

    private final String type;
    private final String icon;

    NotificationType(String type, String icon) {
        this.type = type;
        this.icon = icon;
    }
}
