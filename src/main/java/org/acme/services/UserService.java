package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.User;
import org.acme.model.dtos.user.UserRequestDTO;
import org.acme.model.dtos.user.UserResponseDTO;
import org.acme.repository.UserRepository;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws Exception {

        if(userRepository.getUserByEmail(userRequestDTO.email()).get(0).getResult().size() > 0) {
            throw new Exception("User already registered");
        }

        User newUser = new User(userRequestDTO);
        User userCreatedResponse = userRepository.createUser(newUser);

        return new UserResponseDTO(userCreatedResponse);
    }

    public List<UserResponseDTO> getAllUsersRequest() {
        return userRepository.getAllUsers().stream()
                .sorted(Comparator.comparing(User::getPoints).reversed())
                .map(UserResponseDTO::new)
                .toList();
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).get(0).getResult().get(0);
    }

    public void updateUserPoints(User user) {
        userRepository.updateUserPoints(user.getEmail(), user.getPoints().toString());
    }

    public UserResponseDTO convertUserToDTO(User user){

        if(user != null) {
            return new UserResponseDTO(user);
        }

        return null;
    }
}
