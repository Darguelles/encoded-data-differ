package com.waes.encodeddatadiffer.core.binaryelement;

import com.waes.encodeddatadiffer.core.binaryelement.enums.CompareStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BinaryElementVO represents the Value Object for the proposed business case. Depending on the information retrieved
 * from database and process, BinaryElementVO will be used as immutable entity for communication with Application layer.
 */
@Getter
@Builder
public class BinaryElementVO {

    @NonNull
    private CompareStatus compareStatus;
    private List<Difference> differences;
}
