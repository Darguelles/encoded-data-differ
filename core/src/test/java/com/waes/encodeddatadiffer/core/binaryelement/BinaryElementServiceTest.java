package com.waes.encodeddatadiffer.core.binaryelement;

import com.waes.encodeddatadiffer.core.binaryelement.enums.CompareStatus;
import com.waes.encodeddatadiffer.core.binaryelement.exceptions.InvalidDataEncryptionException;
import com.waes.encodeddatadiffer.core.binaryelement.exceptions.MissingElementSideException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.waes.encodeddatadiffer.core.binaryelement.mocks.BinaryElementMockGenerator.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BinaryElementServiceTest {

    @Mock
    private BinaryElementQueryAdapter binaryElementQueryAdapter;

    @InjectMocks
    private BinaryElementServiceImpl binaryElementService;

    @Test
    void shouldSaveBinaryElementWithJustOneSideProvided() {
        BinaryElement element = elementWithOneSide();
        when(binaryElementQueryAdapter.save(element)).thenReturn(element);

        BinaryElement saved = binaryElementService.save(element);

        assertThat(element.getId(), is(saved.getId()));
    }

    @Test
    void whenSavingBinaryElementShouldThrowInvalidDataEncryptionExceptionIfElementIsNotBase64Encoded() {
        BinaryElement element = elementWithBadBase64Encoding();

        assertThrows(InvalidDataEncryptionException.class, () -> {
            binaryElementService.save(element);
        });
    }

    @Test
    void shouldReturnEqualStatusIfBothElementSidesAreTheSame() {
        BinaryElement element = elementWithSameValues();
        when(binaryElementQueryAdapter.getById(DEFAULT_ID)).thenReturn(element);

        BinaryElementVO result = binaryElementService.compareValues(DEFAULT_ID);

        assertThat(result.getCompareStatus(), is(CompareStatus.EQUAL));
    }

    @Test
    void shouldReturnDifferentStatusIfBothElementSidesAreDifferentInSize() {
        BinaryElement element = elementWithTwoDifferentSides();
        when(binaryElementQueryAdapter.getById(DEFAULT_ID)).thenReturn(element);

        BinaryElementVO result = binaryElementService.compareValues(DEFAULT_ID);

        assertThat(result.getCompareStatus(), is(CompareStatus.DIFFERENT_BY_LENGTH));
    }

    @Test
    void shouldReturnDifferencesListWhenElementHasSidesWithEqualSizeAndDifferentData() {
        BinaryElement element = elementWithTwoDifferentSidesWithSameLength();
        when(binaryElementQueryAdapter.getById(DEFAULT_ID)).thenReturn(element);

        BinaryElementVO result = binaryElementService.compareValues(DEFAULT_ID);

        assertThat(result.getCompareStatus(), is(CompareStatus.DIFFERENT_BY_CONTENT));
        assertThat(result.getDifferences().get(0).getResult(), is("en"));
        assertThat(result.getDifferences().get(1).getResult(), is("ssa"));
    }

    @Test
    void shouldThrowMissingElementSideExceptionIfOneElementSideIsNull() {
        BinaryElement element = elementWithOneSide();
        when(binaryElementQueryAdapter.getById(DEFAULT_ID)).thenReturn(element);

        assertThrows(MissingElementSideException.class, () -> {
            binaryElementService.compareValues(DEFAULT_ID);
        });
    }

    @Test
    void shouldGenerateANewIdIfNotSpecifiedInElement() {

    }

}
