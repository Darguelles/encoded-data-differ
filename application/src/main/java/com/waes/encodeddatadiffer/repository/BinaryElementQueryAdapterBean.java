package com.waes.encodeddatadiffer.repository;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import com.waes.encodeddatadiffer.core.binaryelement.persistence.BinaryElementQueryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BinaryElementQueryAdapterBean implements BinaryElementQueryAdapter {

    private BinaryElementMemoryRepository binaryElementMemoryRepository;

    @Autowired
    public BinaryElementQueryAdapterBean(BinaryElementMemoryRepository binaryElementMemoryRepository) {
        this.binaryElementMemoryRepository = binaryElementMemoryRepository;
    }

    @Override
    public BinaryElement save(BinaryElement element) {
        return null;
    }

    @Override
    public BinaryElement getById(Long id) {
        return null;
    }
}
