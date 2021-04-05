package com.waes.encodeddatadiffer.core.binaryelement;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Difference {
    private Integer index;
    private Integer offset;
    private String result;
}
