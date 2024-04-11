package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.model.Campaign;
import org.acme.model.dtos.campaign.CampaignDTO;
import org.acme.services.CampaignService;

@Path("/campaign")
public class CampaignController {

    @Inject
    private CampaignService campaignService;

    @GET
    public Campaign getCampaign(){
        return campaignService.getCurrentCampaign();
    }
    @POST
    @Transactional
    public void updateCampaignPhrase(@Valid CampaignDTO campaignDTO) {
        campaignService.initUpdateCampaignWorkflow(campaignDTO);
    }
}
