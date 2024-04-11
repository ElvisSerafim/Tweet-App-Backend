package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.acme.model.Campaign;
import org.acme.model.Tweet;
import org.acme.model.User;
import org.acme.model.dtos.user.UserResponseDTO;
import org.acme.model.views.tweet.TweetResult;
import org.acme.model.dtos.tweet.TweetRequestDTO;
import org.acme.repository.TweetRepository;


import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class TweetService {
    @Inject
    private TweetRepository tweetRepository;

    @Inject
    private UserService userService;

    @Inject
    private CampaignService campaignService;

    public TweetResult createTweet(TweetRequestDTO tweetRequestDTO) throws Exception {

        User user = userService.getUserByEmail(tweetRequestDTO.userEmail());

        if(user != null) {
            Tweet tweet = new Tweet(tweetRequestDTO);
            Campaign campaign = campaignService.getCurrentCampaign();

            if(tweet.getMessage().toUpperCase().contains(campaign.getCampaignPhrase().toUpperCase())) {
                Integer userNewPoints = user.getPoints() + 10;
                user.setPoints(userNewPoints);
            }

            tweetRepository.createTweet(tweet);
            userService.updateUserPoints(user);

            return new TweetResult(tweet, userService.convertUserToDTO(user));

        }

        throw new Exception("User not found");
    }

    public List<TweetResult> getAllTweets() {
        return tweetRepository.getTweets().stream().sorted(Comparator.comparing(Tweet::getCreatedAt).reversed()).map(tweet -> {
            User user = userService.getUserByEmail(tweet.getUserEmail());
            return new TweetResult(tweet, userService.convertUserToDTO(user));
        }).toList();
    }

    public List<TweetResult> getTweetsByUser(String userEmail) {
        List<Tweet> tweets = tweetRepository.getTweetsByUser(userEmail).get(0).getResult();
        User user = userService.getUserByEmail(userEmail);

        return tweets.stream().map(tweet -> new TweetResult(tweet, userService.convertUserToDTO(user))).toList();
    }

    public void recalcUserTweetPointsByCampaign(String newCampaign) {
        userService.getAllUsers().forEach(user -> {
            List<Tweet> tweets = tweetRepository.getTweetsByUser(user.getEmail()).get(0).getResult();

            user.setPoints(0);
            tweets.forEach(tweet -> {
                if (tweet.getMessage().trim().toUpperCase().contains(newCampaign.toUpperCase())) {
                    user.setPoints(user.getPoints() + 10);
                }
            });
            userService.updateUserPoints(user);
        });
    }
}
