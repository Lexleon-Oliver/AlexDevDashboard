package br.net.alexdev.dashboard.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record Register(
        @NotBlank @Size(min = 3, max = 30)String username,
        Set<String> roles,
        @NotBlank @Email @Size(max =100)String email,

        @NotBlank @Size(max =50)String name,
        @NotBlank @Size(max =30)String jobTitle) {
}
