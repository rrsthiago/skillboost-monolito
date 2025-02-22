package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.model.Professional;
import com.rrsthiago.skillboost.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;

@SpringBootTest
public class ProfessionalServiceIT {

    @Autowired
    private ProfessionalService professionalService;

    private Professional testProfessional;

    @Test
    void shouldTestCreateProfessional_returnSuccess() {
        Professional professionalPayload = createProfessionalPayload();
        testProfessional = professionalService.create(professionalPayload);

        Assertions.assertNotNull(testProfessional);
        Assertions.assertEquals(professionalPayload.getName(), testProfessional.getName());
        Assertions.assertEquals(professionalPayload.getEmail(), testProfessional.getEmail());
        Assertions.assertEquals(professionalPayload.getRegisterNumber(), testProfessional.getRegisterNumber());
        Assertions.assertNotNull(testProfessional.getId());
    }

    @Test
    void shouldTestGetProfessional_returnSuccess() {
        Professional professionalPayload = createProfessionalPayload();
        testProfessional = professionalService.create(professionalPayload);
        BigInteger professionalId = testProfessional.getId();

        Professional professional = professionalService.get(professionalId);

        Assertions.assertNotNull(professional);
        Assertions.assertEquals(professionalPayload.getName(), professional.getName());
        Assertions.assertEquals(professionalPayload.getEmail(), professional.getEmail());
        Assertions.assertEquals(professionalPayload.getRegisterNumber(), professional.getRegisterNumber());
    }

    @Test
    void shouldTestGetProfessionalsList_returnSuccess() {
        Professional professionalPayload = createProfessionalPayload();
        testProfessional = professionalService.create(professionalPayload);

        List<Professional> professionals = professionalService.list();

        Assertions.assertNotEquals(0, professionals.size());
    }

    @Test
    void shouldTestUpdateProfessional_returnSuccess() {
        Professional professionalPayload = createProfessionalPayload();
        testProfessional = professionalService.create(professionalPayload);
        BigInteger professionalId = testProfessional.getId();

        String updatedName = "Nome Alterado";
        String updatedEmail = "alterado@email.com";
        String updatedRegisterNumber = "654321";
        Professional updatePayload = Professional.builder()
                .name(updatedName)
                .email(updatedEmail)
                .registerNumber(updatedRegisterNumber)
                .user(User.builder()
                        .id(BigInteger.ONE)
                        .build())
                .build();

        Professional updatedProfessional = professionalService.update(professionalId, updatePayload);

        Assertions.assertNotNull(updatedProfessional);
        Assertions.assertEquals(updatedName, updatedProfessional.getName());
        Assertions.assertEquals(updatedEmail, updatedProfessional.getEmail());
        Assertions.assertEquals(updatedRegisterNumber, updatedProfessional.getRegisterNumber());
    }

    @Test
    void shouldTestDeleteProfessional_returnSuccess() {
        Professional professionalPayload = createProfessionalPayload();
        testProfessional = professionalService.create(professionalPayload);
        BigInteger professionalId = testProfessional.getId();
        professionalService.delete(professionalId);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> professionalService.get(professionalId));
    }

    private Professional createProfessionalPayload() {
        return Professional.builder()
                .name("Nome")
                .email("e@mail.com")
                .registerNumber("123456")
                .user(User.builder()
                        .id(BigInteger.ONE)
                        .build())
                .build();
    }

    @AfterEach
    void tearDown() {
        if (testProfessional != null && testProfessional.getId() != null) {
            professionalService.delete(testProfessional.getId());
        }
    }

}
