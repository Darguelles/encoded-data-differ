package com.waes.encodeddatadiffer.core.binaryelement;

public interface BinaryElementService {

    BinaryElementVO compareValues(Long id);

    BinaryElement save(BinaryElement binaryElement);
}
