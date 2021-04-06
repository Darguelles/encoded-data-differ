package com.waes.encodeddatadiffer.api;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.dto.DifferenceResponseDTO;
import com.waes.encodeddatadiffer.api.facade.DataDifferFacade;
import com.waes.encodeddatadiffer.core.binaryelement.enums.Side;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/diff")
public class DiffControllerV1 {

    private DataDifferFacade dataDifferFacade;

    @Autowired
    public DiffControllerV1(DataDifferFacade dataDifferFacade) {
        this.dataDifferFacade = dataDifferFacade;
    }

    @Operation(summary = "Save left value", description = "Stores encrypted data in the left side of the structure",
            tags = { "v1" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad input format")
    })
    @PatchMapping("{id}/left")
    public ResponseEntity saveLeft(@PathVariable String id, @RequestBody DataRequestDTO requestDTO) {
        requestDTO.setSide(Side.LEFT.name());
        dataDifferFacade.saveElement(requestDTO);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Operation(summary = "Save right value", description = "Stores encrypted data in the right side of the structure",
            tags = { "v1" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad input format")
    })
    @PatchMapping("{id}/right")
    public ResponseEntity saveRight(@PathVariable String id, @RequestBody DataRequestDTO requestDTO) {
        requestDTO.setSide(Side.RIGHT.name());
        dataDifferFacade.saveElement(requestDTO);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Operation(summary = "Get differences", description = "Retrieve differences between information stored on each "
            + "side of the structure for a given ID", tags = { "v1" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
            content = @Content(schema = @Schema(implementation = DifferenceResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad input format"),
            @ApiResponse(responseCode = "404", description = "The specified resource does not exists."),
            @ApiResponse(responseCode = "422", description = "Cannot perform operation. Missing parameters.")
    })
    @GetMapping(path = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DifferenceResponseDTO> getDiff(@PathVariable String id) {
        DifferenceResponseDTO response = dataDifferFacade.compareElementData(Long.valueOf(id));
        return ResponseEntity.ok(response);
    }

}
