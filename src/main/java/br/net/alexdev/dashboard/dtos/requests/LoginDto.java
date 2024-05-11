package br.net.alexdev.dashboard.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank String username, @NotBlank String password) {
}
