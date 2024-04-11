package org.acme.workflows;


import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.dtos.campaign.CampaignDTO;

@WorkflowInterface
public interface CampaignWorkflow {

    @WorkflowMethod
    void updateCampaign(CampaignDTO campaignDTO);

    @SignalMethod
    void exit();
}
