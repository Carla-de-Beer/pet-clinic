package com.cadebe.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseEntity implements Serializable {

    // boxed types can be null whereas primitives cannot. Requirement from JPA to use boxed types.
    private Long id;
}
