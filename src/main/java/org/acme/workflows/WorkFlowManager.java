package org.acme.workflows;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.dtos.campaign.CampaignDTO;
import org.acme.services.CampaignService;
import org.acme.services.TweetService;

@ApplicationScoped
public class WorkFlowManager {

    public static final String TASK_QUEUE = "campaignUpdateTaskQueue";
    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    private static final WorkflowClient client = WorkflowClient.newInstance(service);
    private static final WorkerFactory factory = WorkerFactory.newInstance(client);

    @Inject
    TweetService tweetService;

    @Inject
    CampaignService campaignService;

    public void startWorkflow(CampaignDTO campaignDTO) {

        Worker worker = factory.newWorker(TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(CampaignWorkflowImpl.class);
        worker.registerActivitiesImplementations(new CampaignActivitiesImpl(campaignService, tweetService));
        factory.start();

        WorkflowOptions newCampaignWorkflowOptions =
                WorkflowOptions.newBuilder()
                        .setWorkflowId("campaignWorkflow-" + campaignDTO.campaignPhrase())
                        .setTaskQueue(TASK_QUEUE)
                        .build();

        CampaignWorkflow newCustomerWorkflow =
                client.newWorkflowStub(CampaignWorkflow.class, newCampaignWorkflowOptions);

        WorkflowClient.start(newCustomerWorkflow::updateCampaign, campaignDTO);
    }
}
