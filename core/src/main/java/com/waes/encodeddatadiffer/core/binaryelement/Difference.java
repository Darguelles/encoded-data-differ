package com.waes.encodeddatadiffer.core.binaryelement;

import lombok.Builder;
import lombok.Getter;

/**
 * Difference represent each value obtained from the comparison algorithm.
 */
@Getter
@Builder
public class Difference {
    private Integer index;
    private Integer offset;
    private String result;
}
