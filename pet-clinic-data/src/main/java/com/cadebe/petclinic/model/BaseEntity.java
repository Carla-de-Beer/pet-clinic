package com.cadebe.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass // means the class won't be mapped to the db
public class BaseEntity implements Serializable {

    // boxed types can be null whereas primitives cannot. Requirement from JPA to use boxed types.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
