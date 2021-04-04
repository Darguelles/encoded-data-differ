package com.waes.encodeddatadiffer.core.register;

public interface BinaryElementService {

    BinaryElementVO compareValues(Long id);

    BinaryElement save(BinaryElement binaryElement);
}
