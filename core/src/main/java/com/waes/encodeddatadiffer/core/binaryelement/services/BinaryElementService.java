package com.waes.encodeddatadiffer.core.binaryelement.services;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElementVO;

import java.util.Optional;

/**
 * BinaryElementService validate business rules and is used to expose the internal core methods to the application layer
 */
public interface BinaryElementService {

    BinaryElementVO compareValues(Long id);

    BinaryElement save(BinaryElement binaryElement);

    Optional<BinaryElement> getById(Long id);
}
