package br.net.alexdev.dashboard.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDto(@NotBlank String token) {
}
