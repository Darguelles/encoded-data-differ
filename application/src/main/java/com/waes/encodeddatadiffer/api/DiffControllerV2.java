package com.waes.encodeddatadiffer.api;

import com.waes.encodeddatadiffer.api.dto.DataRequestDTO;
import com.waes.encodeddatadiffer.api.dto.DataResponseDTO;
import com.waes.encodeddatadiffer.api.dto.DifferenceResponseDTO;
import com.waes.encodeddatadiffer.api.facade.DataDifferFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v2/element")
public class DiffControllerV2 {

    private DataDifferFacade dataDifferFacade;

    @Autowired
    public DiffControllerV2(DataDifferFacade dataDifferFacade) {
        this.dataDifferFacade = dataDifferFacade;
    }

    @Operation(summary = "Save value", description = "Stores encrypted data in one side of the structure",
            tags = { "v2" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = DataResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad input format")
    })
    @PostMapping
    public ResponseEntity<DataResponseDTO> saveLeft(@RequestBody DataRequestDTO requestDTO) {
        DataResponseDTO response = dataDifferFacade.saveElement(requestDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update element", description = "Update encrypted data in one side of the structure",
            tags = { "v2" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Bad input format")
    })
    @PatchMapping("{id}")
    public ResponseEntity updateElement(@PathVariable String id, @RequestBody DataRequestDTO requestDTO) {
        dataDifferFacade.saveElement(requestDTO);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Operation(summary = "Get differences", description = "Retrieve differences between information stored on each " +
            "side of the structure for a given ID", tags = { "v2" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = DifferenceResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad input format"),
            @ApiResponse(responseCode = "404", description = "The specified resource does not exists."),
            @ApiResponse(responseCode = "422", description = "Cannot perform operation. Missing parameters.")
    })
    @GetMapping(path = "{id}/diff",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DifferenceResponseDTO> getDiff(@PathVariable String id) {
        DifferenceResponseDTO response = dataDifferFacade.compareElementData(Long.valueOf(id));
        return ResponseEntity.ok(response);
    }

}
