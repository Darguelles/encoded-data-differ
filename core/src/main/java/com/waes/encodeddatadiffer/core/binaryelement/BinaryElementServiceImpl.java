package com.waes.encodeddatadiffer.core.binaryelement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BinaryElementServiceImpl implements BinaryElementService{

    private BinaryElementQueryAdapter repository;

    @Autowired
    public BinaryElementServiceImpl(BinaryElementQueryAdapter repository) {
        this.repository = repository;
    }

    @Override
    public BinaryElementVO compareValues(Long id) {
        return null;
    }

    @Override
    public BinaryElement save(BinaryElement binaryElement) {
        return null;
    }
}
