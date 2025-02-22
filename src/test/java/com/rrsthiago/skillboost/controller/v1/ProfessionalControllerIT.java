package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.model.Professional;
import com.rrsthiago.skillboost.model.User;
import com.rrsthiago.skillboost.repository.ProfessionalRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigInteger;

import static io.restassured.RestAssured.given;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfessionalControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    ProfessionalRepository professionalRepository;

    private BigInteger testProfessionalId;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/v1/professionals";
    }

    @Test
    void shouldTestCreateProfessional_validPayload_returnSuccess() {
        Integer id =
                given()
                        .body("{\n" +
                                "    \"name\": \"Nome Completo\",\n" +
                                "    \"email\": \"email@email.com\",\n" +
                                "    \"registerNumber\": \"0001\",\n" +
                                "    \"userId\": 1\n" +
                                "}")
                        .contentType("application/json")
                        .accept("application/json")
                .when()
                        .post()
                .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .path("id");
        testProfessionalId = BigInteger.valueOf(id);
    }

    @Test
    void shouldTestCreateProfessional_invalidPayload_returnSuccess() {
        given()
                .body("{}")
                .contentType("application/json")
                .accept("application/json")
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("detail", org.hamcrest.Matchers.containsString("Preenchimento invalido de um ou mais atributos"));
    }

    @Test
    void shouldTestGetProfessionalById_returnSuccess() {
        createTestProfessional();

        given()
                .pathParam("id", testProfessionalId)
        .when()
                .get("/{id}")
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldTestGetProfessionalById_returnNotFound() {
        given()
                .pathParam("id", 0)
        .when()
                .get("/{id}")
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("detail", org.hamcrest.Matchers.containsString("Resource not found with id"));
    }

    @Test
    void shoudTestGetProfessionalList_returnSuccess() {
        createTestProfessional();

        given()
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", org.hamcrest.Matchers.greaterThan(0));
    }

    @Test
    void shouldTestUpdateProfessional_returnSuccess() {
        createTestProfessional();
        String updatedName = "Nome Alterado";
        String updatedEmail = "alterado@email.com";
        String updatedRegisterNumber = "654321";

        given()
                .pathParam("id", testProfessionalId)
                .body("{\n" +
                        "    \"name\": \"" + updatedName + "\",\n" +
                        "    \"email\": \"" + updatedEmail + "\",\n" +
                        "    \"registerNumber\": \"" + updatedRegisterNumber + "\",\n" +
                        "    \"userId\": 1\n" +
                        "}")
                .contentType("application/json")
                .accept("application/json")
        .when()
                .put("/{id}")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", org.hamcrest.Matchers.equalTo(updatedName))
                .body("email", org.hamcrest.Matchers.equalTo(updatedEmail))
                .body("registerNumber", org.hamcrest.Matchers.equalTo(updatedRegisterNumber));
    }

    @Test
    void shouldTestDeleteProfessional_returnSuccess() {
        createTestProfessional();

        given()
                .pathParam("id", testProfessionalId)
        .when()
                .delete("/{id}")
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void createTestProfessional() {
        Professional testProfessional = professionalRepository.save(Professional.builder()
                .name("Nome Completo")
                .email("email@mail.com")
                .registerNumber("0001")
                .user(User.builder()
                        .id(BigInteger.ONE)
                        .build())
                .build());
        testProfessionalId = testProfessional.getId();
    }

    @AfterEach
    public void tearDown() {
        if (testProfessionalId != null) {
            professionalRepository.deleteById(testProfessionalId);
            testProfessionalId = null;
        }
    }

}
