package br.net.alexdev.dashboard.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record ThemeDto(@NotBlank String theme) {
}
