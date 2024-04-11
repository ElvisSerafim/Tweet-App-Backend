package org.acme.workflows;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.dtos.campaign.CampaignDTO;
import org.acme.services.CampaignService;
import org.acme.services.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CampaignActivitiesImpl implements CampaignActivities {

    private static final Logger log = LoggerFactory.getLogger(CampaignActivitiesImpl.class);

    private TweetService tweetService;

    private CampaignService campaignService;

    public CampaignActivitiesImpl(CampaignService campaignService, TweetService tweetService){
        this.campaignService = campaignService;
        this.tweetService = tweetService;
    }

    @Override
    public void updateCampaign(CampaignDTO campaignDTO) {
        this.campaignService.updateCampaignPhrase(campaignDTO);
        sleepSeconds(1);
    }

    @Override
    public void updateUserPoints(String newCampaign) {
        this.tweetService.recalcUserTweetPointsByCampaign(newCampaign);
        sleepSeconds(1);
    }

    private void sleepSeconds(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Exception in thread sleep: ", e);
        }
    }
}
