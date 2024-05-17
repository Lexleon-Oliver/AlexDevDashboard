package br.net.alexdev.dashboard.dtos.responses;

import jakarta.validation.constraints.NotBlank;

public record ErrorMessageResponse(@NotBlank String erro, @NotBlank String mensagem, @NotBlank String tipo,
                                   @NotBlank int status) {
}
