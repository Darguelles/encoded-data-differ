package com.waes.encodeddatadiffer.core.register;

import com.waes.encodeddatadiffer.core.register.enums.Side;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Register {
    private Long id;
    private Side left;
    private Side right;
}
