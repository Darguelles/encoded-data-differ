package com.waes.encodeddatadiffer.repository;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import com.waes.encodeddatadiffer.core.binaryelement.persistence.BinaryElementQueryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BinaryElementQueryAdapterBean implement the core interface BinaryElementQueryAdapter.
 * Performs integration between core contract and repository implementation.
 */
@Component
public class BinaryElementQueryAdapterBean implements BinaryElementQueryAdapter {

    private BinaryElementMemoryRepository binaryElementMemoryRepository;

    @Autowired
    public BinaryElementQueryAdapterBean(BinaryElementMemoryRepository binaryElementMemoryRepository) {
        this.binaryElementMemoryRepository = binaryElementMemoryRepository;
    }

    @Override
    public BinaryElement save(BinaryElement element) {
        return binaryElementMemoryRepository.save(element);
    }

    @Override
    public BinaryElement getById(Long id) {
        return binaryElementMemoryRepository.getByElementId(id);
    }
}
