package com.cadebe.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Speciality extends BaseEntity {

    private String description;
}
