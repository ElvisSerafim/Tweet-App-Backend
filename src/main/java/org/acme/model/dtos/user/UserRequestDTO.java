package org.acme.model.dtos.user;

import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(@NotNull String email, @NotNull String password, @NotNull String name) {
}
