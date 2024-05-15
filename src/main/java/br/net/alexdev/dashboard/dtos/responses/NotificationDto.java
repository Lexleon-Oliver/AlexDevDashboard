package br.net.alexdev.dashboard.dtos.responses;

import jakarta.validation.constraints.NotBlank;

public record NotificationDto(
        @NotBlank Long id,
        @NotBlank String type,
        @NotBlank String title,
        @NotBlank String message,
        @NotBlank String createdAt,
        @NotBlank String icon,
        String readedAt
) {
}
