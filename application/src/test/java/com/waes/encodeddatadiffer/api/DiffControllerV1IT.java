package com.waes.encodeddatadiffer.api;

import com.waes.encodeddatadiffer.EncodedDataDifferApplicationTests;
import com.waes.encodeddatadiffer.api.facade.DataDifferFacade;
import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {EncodedDataDifferApplicationTests.class})
@AutoConfigureMockMvc
public class DiffControllerV1IT {

    @Autowired
    private DataDifferFacade dataDifferFacade;

    @Autowired
    private MockMvc mvc;

    private String jsonPayloadSmallData = "{\n" +
            "\"id\":\"273645213\",\n" +
            "\"data\":\"ZW5jb2RlZCBtZXNzYWdl\"\n" +
            "}";

    private String jsonPayloadSmallDataDifferentValue = "{\n" +
            "\"id\":\"273645213\",\n" +
            "\"data\":\"cmVjb2RlZCBtZWxlZWdl\"\n" +
            "}";

    private String jsonPayloadLargerData = "{\n" +
            "\"id\":\"273645213\",\n" +
            "\"data\":\"b3RoZXIgZW5jb2RlZCBtZXNzYWdl\"\n" +
            "}";

    @Test
    void shouldSaveElementWithLeftSideData() throws Exception  {
        //Given
        final String elementID = "273645213";

        //Then
        mvc.perform(patch("/v1/diff/{id}/left", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        BinaryElement stored = dataDifferFacade.getByElementID(Long.valueOf(elementID));
        assertTrue(stored.getLeft().equals("ZW5jb2RlZCBtZXNzYWdl"));
    }

    @Test
    void shouldSaveElementWithRightSideData() throws Exception  {
        //Given
        final String elementID = "273645213";

        //Then
        mvc.perform(patch("/v1/diff/{id}/right", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        BinaryElement stored = dataDifferFacade.getByElementID(Long.valueOf(elementID));
        assertTrue(stored.getRight().equals("ZW5jb2RlZCBtZXNzYWdl"));
    }

    @Test
    void shouldReturnNoDifferencesFromTwoSameElementSides() throws Exception {
        //Given
        final String elementID = "273645213";

        //Then
        mvc.perform(patch("/v1/diff/{id}/left", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(patch("/v1/diff/{id}/right", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(get("/v1/diff/{id}", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status",is("EQUAL")))
                .andExpect(jsonPath("$.totalDifferences",is(0)));
    }

    @Test
    void shouldReturnNoDifferencesBetweenSidesDifferentLength() throws Exception {
        //Given
        final String elementID = "273645213";

        //Then
        mvc.perform(patch("/v1/diff/{id}/left", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(patch("/v1/diff/{id}/right", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadLargerData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(get("/v1/diff/{id}", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status",is("DIFFERENT_BY_LENGTH")))
                .andExpect(jsonPath("$.totalDifferences",is(0)));
    }

    @Test
    void shouldReturnDifferencesListWhenBothSidesHasSameLengthAndDifferentValue() throws Exception {
        //Given
        final String elementID = "273645213";

        //Then
        mvc.perform(patch("/v1/diff/{id}/left", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallData))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(patch("/v1/diff/{id}/right", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayloadSmallDataDifferentValue))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mvc.perform(get("/v1/diff/{id}", elementID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.status",is("DIFFERENT_BY_CONTENT")))
                .andExpect(jsonPath("$.totalDifferences",is(2)));
    }
}
