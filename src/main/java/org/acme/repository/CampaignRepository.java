package org.acme.repository;

import com.surrealdb.driver.model.QueryResult;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Campaign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CampaignRepository extends AbstractRepository<Campaign> {

    Map<String, String> mapData = new HashMap<>();

    public CampaignRepository() {
        super("campaign", Campaign.class);
    }

    public List<Campaign> getCampaign() {
        return select();
    }

    public Campaign create(Campaign campaign) {
        return create(campaign);
    }

    public List<QueryResult<Campaign>> updateCampaignPhrase (String oldCampaignPhrase, String campaignPhrase) {
        mapData.put("campaignPhrase", campaignPhrase);
        mapData.put("oldCampaignPhrase", oldCampaignPhrase);
        List<QueryResult<Campaign>> campaignDataQuery = query("UPDATE campaign set campaignPhrase = $campaignPhrase WHERE campaignPhrase = $oldCampaignPhrase", mapData);
        mapData.clear();
        return campaignDataQuery;
    }
}
