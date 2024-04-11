package org.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {

    private String id;

    private String campaignPhrase;

    public Campaign(String campaignPhrase) {
        this.campaignPhrase = campaignPhrase;
    }
}
