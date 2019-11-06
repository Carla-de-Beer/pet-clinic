package com.cadebe.petclinic.repository;

import com.cadebe.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository  extends CrudRepository<Visit, Long> {
}
