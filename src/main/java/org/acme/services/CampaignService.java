package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.acme.repository.CampaignRepository;
import org.acme.model.Campaign;
import org.acme.model.dtos.campaign.CampaignDTO;
import org.acme.workflows.WorkFlowManager;

@ApplicationScoped
public class CampaignService {

    @Inject
    private CampaignRepository campaignRepository;
    @Inject
    private TweetService tweetService;

    @Inject
    WorkFlowManager workFlowManager;

    public Campaign getCurrentCampaign() {
        return campaignRepository.getCampaign().get(0);
    }

    public void updateCampaignPhrase(CampaignDTO campaignDTO) {

        if(!campaignDTO.campaignPhrase().trim().toUpperCase().equals(this.getCurrentCampaign().getCampaignPhrase().toUpperCase())){
            campaignRepository.updateCampaignPhrase(this.getCurrentCampaign().getCampaignPhrase(), campaignDTO.campaignPhrase().trim());
        }
    }

    public void initUpdateCampaignWorkflow(CampaignDTO campaignDTO) {
        workFlowManager.startWorkflow(campaignDTO);
    }
}
