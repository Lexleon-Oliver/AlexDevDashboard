package br.net.alexdev.dashboard.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public record UserDto(
        @NotBlank Long id,
        @NotBlank @Size(max = 30) String username,
        @NotBlank @Size(max = 100) String email,
        @NotBlank @Size(max = 50) String name,
        @Size(max = 30) String company,
        @NotBlank @Size(max = 30) String jobTitle,
        @Size(max = 1000) String about,
        @Size(max = 100) String address,
        @Size(max = 30) String city,
        @Size(max = 15) String phone,
        boolean hasNewNotifications,
        Set<String> roles,
        List<NotificationDto> notifications

) {
}
