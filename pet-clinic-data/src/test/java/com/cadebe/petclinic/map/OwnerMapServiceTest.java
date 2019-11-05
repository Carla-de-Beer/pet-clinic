package com.cadebe.petclinic.map;

import com.cadebe.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Tag("service")
@DisplayName("Test OwnerMapService")
class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private final Long ownerId = 1L;
    private final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    @DisplayName("Test find all")
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size(), "Could not find all");
    }

    @Test
    @DisplayName("Test find by id")
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId(), "Could not find by given id");
    }

    @Test
    @DisplayName("Test find by last name")
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId(), "Could not find by last name");
    }

    @Test
    @DisplayName("Test find by last name (lower case)")
    void findByLastNameLowerCase() {
        Owner smith = ownerMapService.findByLastName("smith");

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId(), "Could not find by last name (lower case)");
    }

    @Test
    @DisplayName("Test find by last name (upper case)")
    void findByLastNameUpperCase() {
        Owner smith = ownerMapService.findByLastName("SMITH");

        assertNotNull(smith);
        assertEquals(ownerId, smith.getId(), "Could not find by last name (upper case)");
    }

    @Test
    @DisplayName("Test find by last name (not found)")
    void findByLastNameNotFound() {
        Owner smith = ownerMapService.findByLastName("foo");

        assertNull(smith, "Could not correctly assert that owner could not be found by last name");
    }

    @Test
    @DisplayName("Test save with existing id")
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId(), "Could not save new owner with existing id");
    }

    @Test
    @DisplayName("Test save without id")
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId(), "Could not save new owner without id");
    }

    @Test
    @DisplayName("Test delete")
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size(), "Could not delete owner");
    }

    @Test
    @DisplayName("Test delete by id")
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size(), "Could not delete owner by id");
    }
}