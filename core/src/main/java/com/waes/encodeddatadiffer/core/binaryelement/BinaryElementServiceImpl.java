package com.waes.encodeddatadiffer.core.binaryelement;

import com.waes.encodeddatadiffer.core.binaryelement.exceptions.InvalidDataEncryptionException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.waes.encodeddatadiffer.core.binaryelement.enums.CompareStatus.*;

@Service
public class BinaryElementServiceImpl implements BinaryElementService{

    private BinaryElementQueryAdapter repository;

    @Autowired
    public BinaryElementServiceImpl(BinaryElementQueryAdapter repository) {
        this.repository = repository;
    }

    @Override
    public BinaryElementVO compareValues(Long id) {
        Optional<BinaryElement> fromDatabase = Optional.ofNullable(repository.getById(id));
        if (fromDatabase.isPresent()) {
            BinaryElement element = fromDatabase.get();
            if (element.getRight().equals(element.getLeft())) {
                return BinaryElementVO.builder().compareStatus(EQUAL).build();
            } else if (element.getRight().length() != element.getLeft().length()) {
                return BinaryElementVO.builder().compareStatus(DIFFERENT_BY_LENGTH).build();
            } else {
                String leftValue = decodeBase64(element.getLeft());
                String rightValue = decodeBase64(element.getRight());
                List<Difference> differences = DifferenceCalculator.scanForDifferences(leftValue, rightValue);

                return BinaryElementVO.builder().compareStatus(DIFFERENT_BY_CONTENT).differences(differences).build();
            }
        }
        return null;
    }


    @Override
    public BinaryElement save(BinaryElement binaryElement) {
        if (isValidElement(binaryElement)) {
            return repository.save(binaryElement);
        }
        return null;
    }

    private boolean isValidElement(BinaryElement binaryElement) {
        boolean valid = false;
        if ( binaryElement.getLeft() != null) {
            valid = isBase64(binaryElement.getLeft());
        }
        if ( binaryElement.getRight() != null) {
            valid = isBase64(binaryElement.getRight());
        }
        return valid;
    }

    private boolean isBase64(String value) {
        if (Base64.isBase64(value)) {
            return true;
        }
        throw new InvalidDataEncryptionException();
    }

    private String decodeBase64(String value) {
        byte[] decodedBytes = Base64.decodeBase64(value);
        return new String(decodedBytes);
    }
}
