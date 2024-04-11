package org.acme.model.views.tweet;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.acme.model.Tweet;
import org.acme.model.dtos.user.UserResponseDTO;

import java.time.LocalDateTime;

public record TweetResult(String id, String message, UserResponseDTO user, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt) {

    public TweetResult(Tweet tweet, UserResponseDTO user) {
        this(tweet.getId(), tweet.getMessage(), user, tweet.getCreatedAt());
    }
}