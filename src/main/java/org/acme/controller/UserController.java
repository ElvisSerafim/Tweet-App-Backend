package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.model.dtos.user.UserRequestDTO;
import org.acme.model.dtos.user.UserResponseDTO;
import org.acme.services.UserService;

import java.util.List;

@Path("/users")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Transactional
    public UserResponseDTO createUser(@Valid UserRequestDTO userRequestDTO) throws Exception {
        return userService.createUser(userRequestDTO);
    }

    @GET
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsersRequest();
    }
}
