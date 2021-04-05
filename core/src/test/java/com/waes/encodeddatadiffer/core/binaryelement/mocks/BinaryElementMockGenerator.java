package com.waes.encodeddatadiffer.core.binaryelement.mocks;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;

public class BinaryElementMockGenerator {

    public static final Long DEFAULT_ID = 23521456L;
    public static final String ENCODED_SMALL_MESSAGE_VALUE = "ZW5jb2RlZCBtZXNzYWdl";
    public static final String ENCODED_LONG_MESSAGE_VALUE = "b3RoZXIgZW5jb2RlZCBtZXNzYWdl";


    public static BinaryElement elementWithOneSide() {
        return BinaryElement.builder()
                .id(DEFAULT_ID)
                .left(ENCODED_SMALL_MESSAGE_VALUE)
                .right(null)
                .build();
    }

}
