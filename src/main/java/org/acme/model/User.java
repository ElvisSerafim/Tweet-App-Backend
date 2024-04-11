package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.acme.model.dtos.user.UserRequestDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String email;

    private String password;

    private String name;

    private Integer points;

    public User(UserRequestDTO userRequestDTO) {
        this.email = userRequestDTO.email();
        this.name = userRequestDTO.name();
        this.password = userRequestDTO.name();
        this.points = 0;
    }
}
