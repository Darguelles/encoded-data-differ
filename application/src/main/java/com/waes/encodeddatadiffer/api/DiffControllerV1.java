package com.waes.encodeddatadiffer.api;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.facade.DataDifferFacade;
import com.waes.encodeddatadiffer.core.binaryelement.enums.Side;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/diff")
public class DiffControllerV1 {

    private DataDifferFacade dataDifferFacade;

    @Autowired
    public DiffControllerV1(DataDifferFacade dataDifferFacade) {
        this.dataDifferFacade = dataDifferFacade;
    }

    @PatchMapping("{id}/left")
    public ResponseEntity saveLeft(@PathVariable String id, @RequestBody DataRequestDTO requestDTO) {
        requestDTO.setSide(Side.LEFT.name());
        dataDifferFacade.saveElement(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("{id}/right")
    public ResponseEntity saveRight(@PathVariable String id, @RequestBody DataRequestDTO requestDTO) {
        requestDTO.setSide(Side.RIGHT.name());
        dataDifferFacade.saveElement(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
