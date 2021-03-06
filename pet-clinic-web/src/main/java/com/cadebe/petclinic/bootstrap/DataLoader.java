package com.cadebe.petclinic.bootstrap;

import com.cadebe.petclinic.model.*;
import com.cadebe.petclinic.service.OwnerService;
import com.cadebe.petclinic.service.PetTypeService;
import com.cadebe.petclinic.service.SpecialityService;
import com.cadebe.petclinic.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();

        if (count < 1) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = PetType.builder()
                .name("Dog")
                .build();
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = PetType.builder()
                .name("Cat")
                .build();
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = Owner.builder().firstName("Michael")
                .lastName("Weston")
                .address("123 Brick Lane")
                .city("Miami")
                .telephone("0345-78958001")
                .build();

        Pet mikesPet = Pet.builder()
                .petType(savedDogPetType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Rosco")
                .build();

        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = Owner.builder().firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Brick Lane")
                .city("Miami")
                .telephone("0345-78958001")
                .build();

        System.out.println("Loaded owners");

        Pet fionasPet = Pet.builder()
                .petType(savedCatPetType)
                .owner(owner2)
                .birthDate(LocalDate.now())
                .name("Just Cat")
                .build();

        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        Speciality speciality1 = Speciality.builder()
                .description("Radiology")
                .build();

        Speciality speciality2 = Speciality.builder()
                .description("Surgery")
                .build();

        Speciality speciality3 = Speciality.builder()
                .description("Dentistry")
                .build();

        Speciality savedRadiology = specialityService.save(speciality1);
        Speciality savedSurgery = specialityService.save(speciality2);
        Speciality savedDentistry = specialityService.save(speciality3);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vet2.getSpecialities().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("Loaded vets");
    }
}
