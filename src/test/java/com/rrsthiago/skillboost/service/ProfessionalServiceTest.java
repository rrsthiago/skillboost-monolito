package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.exception.UserDoesntExistException;
import com.rrsthiago.skillboost.model.Professional;
import com.rrsthiago.skillboost.model.User;
import com.rrsthiago.skillboost.repository.ProfessionalRepository;
import mock.ProfessionalMock;
import mock.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ProfessionalServiceTest {

    @Mock
    private ProfessionalRepository professionalRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProfessionalService professionalService;

    private Professional professional;
    private User user;

    @BeforeEach
    void setUp() {
        professional = ProfessionalMock.getProfessional();
        user = UserMock.getUser();
    }

    @Test
    void shouldTestCreate_validInput_returnSuccess() {
        Mockito.when(userService.get(Mockito.any())).thenReturn(user);
        Mockito.when(professionalRepository.save(Mockito.any())).thenReturn(professional);

        Professional createdProfessional = professionalService.create(professional);

        Assertions.assertEquals(professional, createdProfessional);
        Mockito.verify(userService, times(1)).get(Mockito.any());
        Mockito.verify(professionalRepository, times(1)).save(Mockito.any());
    }

    @Test
    void shouldTestCreate_UserDoesntExist_throwsException() {
        Mockito.when(userService.get(Mockito.any())).thenThrow(new ResourceNotFoundException(user.getId()));

        Assertions.assertThrows(UserDoesntExistException.class,
                () -> professionalService.create(professional));

        Mockito.verify(userService, times(1)).get(Mockito.any());
        Mockito.verify(professionalRepository, times(0)).save(Mockito.any());
    }

    @Test
    void shouldTestUpdate_validInput_returnSuccess() {
        Mockito.when(professionalRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(professional));
        Mockito.when(professionalRepository.save(Mockito.any())).thenReturn(professional);

        Professional updatedProfessional = professionalService.update(professional.getId(), professional);

        Assertions.assertEquals(professional, updatedProfessional);
        Mockito.verify(professionalRepository, times(1)).findById(Mockito.any());
        Mockito.verify(professionalRepository, times(1)).save(Mockito.any());
    }

    @Test
    void shouldTestUpdate_ProfessionalDoesntExist_throwsException() {
        Mockito.when(professionalRepository.findById(Mockito.any())).thenThrow(new ResourceNotFoundException(professional.getId()));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> professionalService.update(professional.getId(), professional));

        Mockito.verify(professionalRepository, times(1)).findById(Mockito.any());
        Mockito.verify(professionalRepository, times(0)).save(Mockito.any());
    }

    @Test
    void shouldTestGet_professionalExists_returnSuccess() {
        Mockito.when(professionalRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(professional));

        Professional foundProfessional = professionalService.get(professional.getId());

        Assertions.assertEquals(professional, foundProfessional);
        Mockito.verify(professionalRepository, times(1)).findById(Mockito.any());
    }

    @Test
    void shouldTestGet_professionalDoesntExist_throwsException() {
        Mockito.when(professionalRepository.findById(Mockito.any())).thenThrow(new ResourceNotFoundException(professional.getId()));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> professionalService.get(professional.getId()));

        Mockito.verify(professionalRepository, times(1)).findById(Mockito.any());
    }

    @Test
    void shouldTestList_returnSuccess() {
        Mockito.when(professionalRepository.findAll()).thenReturn(java.util.List.of(professional));

        java.util.List<Professional> professionals = professionalService.list();

        Assertions.assertEquals(java.util.List.of(professional), professionals);
        Mockito.verify(professionalRepository, times(1)).findAll();
    }

    @Test
    void shouldTestList_returnEmptyList() {
        Mockito.when(professionalRepository.findAll()).thenReturn(java.util.List.of());

        java.util.List<Professional> professionals = professionalService.list();

        Assertions.assertEquals(java.util.List.of(), professionals);
        Mockito.verify(professionalRepository, times(1)).findAll();
    }

    @Test
    void shouldTestDelete_returnSuccess() {
        Mockito.doNothing().when(professionalRepository).deleteById(Mockito.any());

        professionalService.delete(professional.getId());

        Mockito.verify(professionalRepository, times(1)).deleteById(Mockito.any());
    }

}
