package com.waes.encodeddatadiffer.core.binaryelement.services;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElementVO;

public interface BinaryElementService {

    BinaryElementVO compareValues(Long id);

    BinaryElement save(BinaryElement binaryElement);
}
