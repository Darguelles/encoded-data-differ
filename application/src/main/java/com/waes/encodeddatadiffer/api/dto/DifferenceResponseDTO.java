package com.waes.encodeddatadiffer.api.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DifferenceResponseDTO {

    @Schema(description = "Comparison status for the given ID.",
            example = "EQUAL", required = true)
    private String status;

    @Schema(description = "Number of found differences",
            example = "2")
    private Integer totalDifferences;

    @ArraySchema(arraySchema = @Schema(implementation = String.class, description = "Detail of each difference",
            example = "Difference: Index: 3, Offset: 6, Value: some"))
    private List<String> differences;
}
