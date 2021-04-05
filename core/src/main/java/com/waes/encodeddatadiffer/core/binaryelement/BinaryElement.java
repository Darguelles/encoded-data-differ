package com.waes.encodeddatadiffer.core.binaryelement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BinaryElement {
    private Long id;
    private String left;
    private String right;
}
