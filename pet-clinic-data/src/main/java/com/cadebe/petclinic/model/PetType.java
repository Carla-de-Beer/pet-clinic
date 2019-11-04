package com.cadebe.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@Table(name = "type")
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;
}
