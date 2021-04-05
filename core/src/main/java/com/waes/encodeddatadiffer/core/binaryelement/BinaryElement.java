package com.waes.encodeddatadiffer.core.binaryelement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
public class BinaryElement {

    @Id
    private Long id;
    private String left;
    private String right;
}
