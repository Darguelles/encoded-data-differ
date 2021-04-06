package com.waes.encodeddatadiffer.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DataResponseDTO {

    private String id;
    private String left;
    private String right;

}
