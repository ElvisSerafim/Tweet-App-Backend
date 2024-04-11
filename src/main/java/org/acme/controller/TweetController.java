package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.model.views.tweet.TweetResult;
import org.acme.model.dtos.tweet.TweetRequestDTO;
import org.acme.services.TweetService;

import java.util.List;

@Path("/tweets")
public class TweetController {

    @Inject
    private TweetService tweetService;

    @POST
    @Transactional
    public TweetResult createTweet(@Valid TweetRequestDTO tweetRequestDTO) throws Exception {
        return tweetService.createTweet(tweetRequestDTO);
    }

    @GET
    public List<TweetResult> getTweets() {
        return tweetService.getAllTweets();
    }

}
