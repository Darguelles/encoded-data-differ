package com.waes.encodeddatadiffer.api.facade;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.dto.DifferenceResponseDTO;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;

public interface DataDifferFacade {

    void saveElement(DataRequestDTO requestDTO);
    DifferenceResponseDTO compareElementData(Long id);
    BinaryElement getByElementID(Long id);

}
