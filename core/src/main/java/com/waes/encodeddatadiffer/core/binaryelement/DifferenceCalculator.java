package com.waes.encodeddatadiffer.core.binaryelement;

import java.util.ArrayList;
import java.util.List;

/**
 * DifferenceCalculator uses an algorithm to calculate where differences are present in 2 given strings.
 */
public class DifferenceCalculator {

    /**
     * Receive 2 String and scan to find differences between each one.
     * @param left first String to compare
     * @param right second String to compare
     * @return List of Difference
     *
     * @see Difference
     */
    public static List<Difference> scanForDifferences(String left, String right) {
        List<Difference> result = new ArrayList<>();
        String[] leftArray = left.split("");
        String[] rightArray = right.split("");

        int index = -1;
        for (int i = 0; i < leftArray.length - 1; i++) {
            // detect for different characters
            if (!leftArray[i].equals(rightArray[i])) {
                if (index < 0) {
                    // Start difference scan
                    index = i;
                }
            } else {
                if (index >= 0) { // There's a difference scan in progress
                    // Store difference in array
                    result.add(Difference.builder()
                            .index(index).offset(i).result(left.substring(index, i)).build());
                    // Reset index
                    index = -1;
                }
            }
        }
        return result;
    }

}
