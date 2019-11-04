package com.cadebe.petclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@Table(name = "pet")
public class Pet extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "owner_id") // tells Hibernate how these two entities are related at db level
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;
}
