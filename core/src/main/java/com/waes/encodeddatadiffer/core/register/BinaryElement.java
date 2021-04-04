package com.waes.encodeddatadiffer.core.register;

import com.waes.encodeddatadiffer.core.register.enums.Side;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryElement {
    private Long id;
    private String left;
    private String right;
}
