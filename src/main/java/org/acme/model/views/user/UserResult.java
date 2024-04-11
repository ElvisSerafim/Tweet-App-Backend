package org.acme.model.views.user;

import org.acme.model.Tweet;
import org.acme.model.User;
import java.util.List;

public record UserResult(String id, String email, String name, List<Tweet> tweets, Integer points) {

    public UserResult(List<Tweet> tweets, User user) {
        this(user.getId(), user.getEmail(), user.getName(), tweets, user.getPoints());
    }
}