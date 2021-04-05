package com.waes.encodeddatadiffer.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Builder
@Getter
public class DifferenceResponseDTO {
    private String status;
    private Integer totalDifferences;
    private List<String> differences;
}
