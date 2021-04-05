package com.waes.encodeddatadiffer.core.binaryelement;

import com.waes.encodeddatadiffer.core.binaryelement.enums.CompareStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
@Builder
public class BinaryElementVO {

    @NonNull
    private CompareStatus compareStatus;
    private List<Difference> differences;
}
