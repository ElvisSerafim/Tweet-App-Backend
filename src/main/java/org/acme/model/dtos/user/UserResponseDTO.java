package org.acme.model.dtos.user;

import org.acme.model.User;

public record UserResponseDTO (String id, String email, String name, Integer points) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getEmail(), user.getName(), user.getPoints());
    }
}