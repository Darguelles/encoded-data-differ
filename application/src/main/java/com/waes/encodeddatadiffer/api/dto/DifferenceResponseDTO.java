package com.waes.encodeddatadiffer.api.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class DifferenceResponseDTO {
    private String status;
    private List<String> differences;
}
