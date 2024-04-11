package org.acme.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.acme.model.dtos.tweet.TweetRequestDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Tweet {

    private String id;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private String userEmail;

    public Tweet(TweetRequestDTO tweetRequestDTO) {
        this.createdAt = tweetRequestDTO.createdAt();
        this.userEmail = tweetRequestDTO.userEmail();
        this.message = tweetRequestDTO.message();
    }
}
