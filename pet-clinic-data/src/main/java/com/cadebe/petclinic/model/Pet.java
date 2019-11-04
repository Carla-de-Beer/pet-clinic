package com.cadebe.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Pet extends BaseEntity {

    private  String name;
    private LocalDate birthdate;
    private Owner owner;
    private PetType petType;
}
