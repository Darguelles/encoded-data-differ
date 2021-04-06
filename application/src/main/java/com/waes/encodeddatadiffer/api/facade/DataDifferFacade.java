package com.waes.encodeddatadiffer.api.facade;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.dto.DataResponseDTO;
import com.waes.encodeddatadiffer.api.dto.DifferenceResponseDTO;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;

/**
 * DataDifferFacade is used as nexus for consume the core services and translate retrieved data into desired resources
 * for API
 */
public interface DataDifferFacade {

    DataResponseDTO saveElement(DataRequestDTO requestDTO);
    DifferenceResponseDTO compareElementData(Long id);
    BinaryElement getByElementID(Long id);

}
