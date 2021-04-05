package com.waes.encodeddatadiffer.api.facade;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.dto.DifferenceResponseDTO;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElementVO;
import com.waes.encodeddatadiffer.core.binaryelement.services.BinaryElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DataDifferFacadeBean implements DataDifferFacade {

    private BinaryElementService binaryElementService;

    @Autowired
    public DataDifferFacadeBean(BinaryElementService binaryElementService) {
        this.binaryElementService = binaryElementService;
    }

    @Override
    public void saveElement(DataRequestDTO requestDTO) {
        Long elementID = Long.parseLong(requestDTO.getId());
        BinaryElement element;
        Optional<BinaryElement> fromDatabase = binaryElementService.getById(elementID);
        if (fromDatabase.isPresent()) {
            element = fromDatabase.get();
            switch (requestDTO.getSide()) {
                case "LEFT":
                    element.setLeft(requestDTO.getData());
                    break;

                case "RIGHT":
                    element.setRight(requestDTO.getData());
                    break;
                default:
                    element = BinaryElement.builder()
                            .elementId(elementID)
                            .build();
            }
        } else {
            switch (requestDTO.getSide()) {
                case "LEFT" :
                    element = BinaryElement.builder()
                            .elementId(elementID)
                            .left(requestDTO.getData())
                            .build();
                    break;

                case "RIGHT" :
                    element = BinaryElement.builder()
                            .elementId(elementID)
                            .right(requestDTO.getData())
                            .build();
                    break;

                default:
                    element = BinaryElement.builder()
                            .elementId(elementID)
                            .build();
            }
        }

        binaryElementService.save(element);
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
        if (fromDatabase.isPresent())
            return fromDatabase.get();

        return null;
    }
}
