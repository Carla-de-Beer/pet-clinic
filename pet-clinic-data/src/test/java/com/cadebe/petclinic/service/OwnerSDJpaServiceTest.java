package com.cadebe.petclinic.service;

import com.cadebe.petclinic.model.Owner;
import com.cadebe.petclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test OwnerSDJpaService")
class OwnerSDJpaServiceTest {

    private final String LAST_NAME = "Smith";
    private final Long OWNER_ID = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService service;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder()
                .id(OWNER_ID)
                .lastName(LAST_NAME)
                .build();
    }

    @Test
    @DisplayName("Test find by last name")
    void findByLastName() {
        // given
        given(ownerRepository.findByLastName(LAST_NAME)).willReturn(owner);

        // when
        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(owner);
        Owner owner = service.findByLastName(LAST_NAME);

        assertThat(owner.getLastName())
                .withFailMessage("Could not find owner by last name")
                .isEqualTo(LAST_NAME);

        // then
        then(ownerRepository).should().findByLastName(anyString());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    @DisplayName("Test find owners by last name like")
    void findOwnersByLastNameLike() {
        // when
        when(ownerRepository.findByLastNameLike(LAST_NAME)).thenReturn(Collections.singletonList(owner));
        List<Owner> ownerList = service.findAllByLastNameLike(LAST_NAME);

        assertThat(ownerList.size())
                .withFailMessage("Could not find owners by last name like")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Test find all")
    void findAll() {
        // given
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder()
                .id(1L)
                .build());
        ownerSet.add(Owner.builder()
                .id(2L)
                .build());

        given(ownerRepository.findAll()).willReturn(ownerSet);

        // when
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> result = service.findAll();

        // then
        then(ownerRepository).should().findAll();

        verify(ownerRepository).findAll();
    }

    @Test
    @DisplayName("Test find by id")
    void findById() {
        // given
        given(ownerRepository.findById(anyLong())).willReturn(Optional.ofNullable(owner));

        // when
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(owner));
        Owner result = service.findById(OWNER_ID);

        // then
        then(ownerRepository).should().findById(anyLong());

        assertThat(result)
                .withFailMessage("Could not find owner by id")
                .isEqualTo(owner);

        assertThat(result.getLastName())
                .withFailMessage("Could not find owner by id")
                .isEqualTo(LAST_NAME);

        verify(ownerRepository).findById(OWNER_ID);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    @DisplayName("Test find by id (not found)")
    void findByIdNotFound() {
        // given
        given(ownerRepository.findById(anyLong())).willReturn(Optional.empty());

        // when
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner result = service.findById(100L);

        // then
        then(ownerRepository).should().findById(anyLong());

        assertThat(result)
                .withFailMessage("Could not find owner by id (not found)")
                .isEqualTo(null);

        verify(ownerRepository).findById(100L);
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    @DisplayName("Test save")
    void save() {
        // when
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        Owner result = service.save(owner);

        // then
        assertThat(result)
                .withFailMessage("Could not save new owner")
                .isEqualTo(owner);

        verify(ownerRepository).save(any(Owner.class));
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    @DisplayName("Test save without id")
    void saveWithoutId() {
        // when
        Owner ownerWithoutId = Owner.builder()
                .lastName(LAST_NAME)
                .build();

        when(ownerRepository.save(any(Owner.class))).thenReturn(ownerWithoutId);
        Owner result = service.save(ownerWithoutId);

        // then
        assertThat(result)
                .withFailMessage("Could not save new owner without id")
                .isEqualTo(ownerWithoutId);

        verify(ownerRepository).save(any(Owner.class));
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    @DisplayName("Test delete")
    void delete() {
        // when
        service.delete(owner);

        // then
        verify(ownerRepository).delete(any(Owner.class));
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    @DisplayName("Test delete by id")
    void deleteById() {
        // when
        service.deleteById(OWNER_ID);

        // then
        verify(ownerRepository).deleteById(anyLong());
        verifyNoMoreInteractions(ownerRepository);
    }
}