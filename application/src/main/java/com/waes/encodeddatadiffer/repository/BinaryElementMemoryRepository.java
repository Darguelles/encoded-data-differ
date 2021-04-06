package com.waes.encodeddatadiffer.repository;

import com.waes.encodeddatadiffer.core.binaryelement.BinaryElement;
import org.springframework.data.repository.CrudRepository;

/**
 * BinaryElementMemoryRepository integrates with memory database H2 using Spring Data JPA.
 */
public interface BinaryElementMemoryRepository extends CrudRepository<BinaryElement, Long> {

    BinaryElement getByElementId(Long id);
}
