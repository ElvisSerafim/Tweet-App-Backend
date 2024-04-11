package org.acme.repository;

import com.surrealdb.driver.model.QueryResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Tweet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TweetRepository extends AbstractRepository<Tweet> {

    Map<String, String> mapData = new HashMap<>();

    @Inject
    UserRepository userRepository;

    public TweetRepository() {
        super("tweets", Tweet.class);
    }

    public Tweet createTweet(Tweet tweet) {


        return create(tweet);
    }

    public List<Tweet> getTweets() {
        return select();
    }

    public List<QueryResult<Tweet>> getTweetsByUser(String userEmail) {
        mapData.put("userEmail", userEmail);
        List<QueryResult<Tweet>> tweetsDataQuery =  query("SELECT * FROM tweets WHERE userEmail = $userEmail", mapData);
        mapData.clear();
        return tweetsDataQuery;
    }
}
