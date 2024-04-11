package org.acme.model.dtos.tweet;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TweetRequestDTO(@NotNull String userEmail, @NotNull String message,@NotNull @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt) {
}
