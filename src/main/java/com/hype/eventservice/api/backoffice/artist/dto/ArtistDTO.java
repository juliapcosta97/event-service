package com.hype.eventservice.api.backoffice.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {

    private BigInteger id;
    private String name;
    private String photo;
    private String description;
}
