package com.waes.encodeddatadiffer.core.binaryelement;

import com.waes.encodeddatadiffer.core.binaryelement.exceptions.InvalidDataEncryptionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.waes.encodeddatadiffer.core.binaryelement.mocks.BinaryElementMockGenerator.elementWithBadBase64Encoding;
import static com.waes.encodeddatadiffer.core.binaryelement.mocks.BinaryElementMockGenerator.elementWithOneSide;
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
    }

    @Test
    void shouldReturnDifferentStatusIfBothElementSidesAreDifferentInSize() {
    }

    @Test
    void shouldReturnDifferencesListWhenElementHasSidesWithEqualSizeAndDifferentData() {
    }

    @Test
    void shouldThrowMissingElementSideExceptionIfOneElementSideIsNull() {
    }

    @Test
    void shouldGenerateANewIdIfNotSpecifiedInElement() {
    }

    @Test
    void shouldThrowMissingSideExceptionIfOneElementSideIsEmpty() {
    }

}
