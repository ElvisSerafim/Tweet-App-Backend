package org.acme.model.dtos.campaign;

import jakarta.validation.constraints.NotNull;

public record CampaignDTO(@NotNull String campaignPhrase) {
}
