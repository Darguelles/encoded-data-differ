package com.waes.encodeddatadiffer.core.binaryelement;

public interface BinaryElementQueryAdapter {

    BinaryElement save(BinaryElement element);

    BinaryElement getById(Long id);

}
