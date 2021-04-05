package com.waes.encodeddatadiffer.core.binaryelement.persistence;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;

public interface BinaryElementQueryAdapter {

    BinaryElement save(BinaryElement element);

    BinaryElement getById(Long id);

}
