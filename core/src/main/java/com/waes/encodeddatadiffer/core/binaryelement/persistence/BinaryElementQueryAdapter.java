package com.waes.encodeddatadiffer.core.binaryelement.persistence;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;

/**
 * BinaryElementQueryAdapter is used to define the contract for the persistence layer. Is not implemented at core level,
 * just define the methods used to accomplish the business case.
 */
public interface BinaryElementQueryAdapter {

    BinaryElement save(BinaryElement element);

    BinaryElement getById(Long id);

}
