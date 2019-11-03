package com.cadebe.petclinic.service;

import com.cadebe.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
