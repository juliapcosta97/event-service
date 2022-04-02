package com.hype.eventservice.api.backoffice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponseDTO {

    private String status;
    private String message;
}
