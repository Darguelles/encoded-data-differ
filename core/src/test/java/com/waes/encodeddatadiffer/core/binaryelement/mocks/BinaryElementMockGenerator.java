package com.waes.encodeddatadiffer.core.binaryelement.mocks;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;

public class BinaryElementMockGenerator {

    public static final Long DEFAULT_ID = 23521456L;
    public static final String ENCODED_SMALL_MESSAGE_VALUE = "ZW5jb2RlZCBtZXNzYWdl";
    public static final String ENCODED_LONG_MESSAGE_VALUE = "b3RoZXIgZW5jb2RlZCBtZXNzYWdl";
    public static final String BAD_ENCODED_MESSAGE_VALUE = "...";


    public static BinaryElement elementWithOneSide() {
        return BinaryElement.builder()
                .id(DEFAULT_ID)
                .left(ENCODED_SMALL_MESSAGE_VALUE)
                .right(null)
                .build();
    }

    public static BinaryElement elementWithBadBase64Encoding() {
        return BinaryElement.builder()
                .id(DEFAULT_ID)
                .left(ENCODED_SMALL_MESSAGE_VALUE)
                .right(BAD_ENCODED_MESSAGE_VALUE)
                .build();
    }

    public static BinaryElement elementWithTwoDifferentSides() {
        return BinaryElement.builder()
                .id(DEFAULT_ID)
                .left(ENCODED_SMALL_MESSAGE_VALUE)
                .right(ENCODED_LONG_MESSAGE_VALUE)
                .build();
    }

}
