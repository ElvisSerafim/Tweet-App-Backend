package org.acme.workflows;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.dtos.campaign.CampaignDTO;

import java.time.Duration;

@ApplicationScoped
public class CampaignWorkflowImpl implements CampaignWorkflow {

    private boolean exit;

    private final CampaignActivities campaignActivities = Workflow.newActivityStub(CampaignActivities.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(30)).build());

    @Override
    public void updateCampaign(CampaignDTO campaignDTO) {

        campaignActivities.updateUserPoints(campaignDTO.campaignPhrase());
        campaignActivities.updateCampaign(campaignDTO);
        exit();

    }


    @Override
        public void exit() {
            this.exit = true;
        }

}
