package com.waes.encodeddatadiffer.api.facade;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.dto.DataResponseDTO;
import com.waes.encodeddatadiffer.api.dto.DifferenceResponseDTO;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElementVO;
import com.waes.encodeddatadiffer.core.binaryelement.services.BinaryElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DataDifferFacadeBean implements DataDifferFacade {

    private BinaryElementService binaryElementService;

    @Autowired
    public DataDifferFacadeBean(BinaryElementService binaryElementService) {
        this.binaryElementService = binaryElementService;
    }

    @Override
    public DataResponseDTO saveElement(DataRequestDTO requestDTO) {
        Long elementID = getLongID(requestDTO);
        BinaryElement element;

        Optional<BinaryElement> fromDatabase = binaryElementService.getById(elementID);
        if (fromDatabase.isPresent()) {
            element = updateBinaryElement(requestDTO, elementID, fromDatabase);
        } else {
            element = createBinaryElement(requestDTO, elementID);
        }

        BinaryElement saved = binaryElementService.save(element);
        return DataResponseDTO.builder().id(saved.getElementId().toString())
                .left(saved.getLeft()).right(saved.getRight()).build();
    }

    private BinaryElement createBinaryElement(DataRequestDTO requestDTO, Long elementID) {
        BinaryElement element;
        switch (requestDTO.getSide().toUpperCase()) {

          case "LEFT": element = BinaryElement.builder()
                    .elementId(elementID)
                    .left(requestDTO.getData())
                    .build();
                break;

          case "RIGHT": element = BinaryElement.builder()
                    .elementId(elementID)
                    .right(requestDTO.getData())
                    .build();
                break;

          default: element = BinaryElement.builder()
                    .elementId(elementID)
                    .build();
        }
        return element;
    }

    private BinaryElement updateBinaryElement(DataRequestDTO requestDTO, Long elementID,
                                              Optional<BinaryElement> fromDatabase) {
        BinaryElement element;
        element = fromDatabase.get();
        switch (requestDTO.getSide().toUpperCase()) {

          case "LEFT": element.setLeft(requestDTO.getData());
                break;

          case "RIGHT": element.setRight(requestDTO.getData());
                break;

          default: element = BinaryElement.builder()
                        .elementId(elementID)
                        .build();
        }
        return element;
    }

    @Override
    public DifferenceResponseDTO compareElementData(Long id) {
        BinaryElementVO differences = binaryElementService.compareValues(id);
        DifferenceResponseDTO responseDTO = DifferenceResponseDTO.builder()
                .status(differences.getCompareStatus().name())
                .totalDifferences(differences.getDifferences().size())
                .differences(differences.getDifferences().stream().map(diff -> {
                    return "Difference: Index: " + diff.getIndex() + ", Offset: "
                            + diff.getOffset() + ", Value: " + diff.getResult();
                }).collect(Collectors.toList())).build();
        return responseDTO;
    }

    @Override
    public BinaryElement getByElementID(Long id) {
        Optional<BinaryElement> fromDatabase = binaryElementService.getById(id);
        if (fromDatabase.isPresent()) {
            return fromDatabase.get();
        }

        return null;
    }


    private Long getLongID(DataRequestDTO requestDTO) {
        Long elementID;
        if (requestDTO.getId() == null) {
            elementID = new Random().nextLong();
        } else {
            elementID = Long.parseLong(requestDTO.getId());
        }
        return elementID;
    }
}
