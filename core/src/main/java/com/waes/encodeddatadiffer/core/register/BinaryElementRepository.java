package com.waes.encodeddatadiffer.core.register;

public interface BinaryElementRepository {

    BinaryElement save(BinaryElement element);

    BinaryElement getById(Long id);

}
