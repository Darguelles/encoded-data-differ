package com.waes.encodeddatadiffer.core.binaryelement.exceptions;

public class MissingElementSideException extends RuntimeException {

    public MissingElementSideException(String side) {
        super("Missing side to compare the current element: " + side);
    }
}
