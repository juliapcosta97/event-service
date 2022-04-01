package com.hype.eventservice.api.backoffice.interaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InteractionDTO {

    @JsonProperty(value = "user_identity")
    private String userIdentity;

    @JsonProperty(value = "event_id")
    private BigInteger eventId;

    private String action;
}
