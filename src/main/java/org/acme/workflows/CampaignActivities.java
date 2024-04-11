package org.acme.workflows;

import io.temporal.activity.ActivityInterface;
import org.acme.model.dtos.campaign.CampaignDTO;

@ActivityInterface
public interface CampaignActivities {

    void updateCampaign(CampaignDTO campaignDTO);

    void updateUserPoints(String newCampaign);


}
