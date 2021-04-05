package com.waes.encodeddatadiffer.core.binaryelement;

import lombok.*;

import javax.persistence.*;

/**
 * BinaryElement represents a single data structure to store values for left and right sections targeting to the same ID.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class BinaryElement {

    @Id
    private Long elementId;

    @Column(name = "LEFT_VALUE")
    private String left;

    @Column(name = "RIGHT_VALUE")
    private String right;
}
