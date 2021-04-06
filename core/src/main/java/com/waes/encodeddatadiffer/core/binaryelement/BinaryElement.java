package com.waes.encodeddatadiffer.core.binaryelement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BinaryElement represents a single data structure to store values for left and right sections related to the same ID.
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
