package com.cadebe.petclinic.controller;

import com.cadebe.petclinic.model.Owner;
import com.cadebe.petclinic.model.PetType;
import com.cadebe.petclinic.service.OwnerService;
import com.cadebe.petclinic.service.PetService;
import com.cadebe.petclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Test PetController")
@Tag("controller")
@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    private PetService petService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetTypeService petTypeService;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;
    private Owner owner;
    private Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().name("Dog").build());
        petTypes.add(PetType.builder().name("Cat").build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    @DisplayName("Test populate pet types")
    void populatePetTypes() {

    }

    @Test
    @DisplayName("Test find owner")
    void findOwner() {

    }

    @Test
    @DisplayName("Test owner init binder")
    void initOwnerBinder() {
    }
}