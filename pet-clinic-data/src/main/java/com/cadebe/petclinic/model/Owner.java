package com.cadebe.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Owner extends Person {

    private Set<Pet> pets;
}
