package com.rrsthiago.skillboost.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionalDto {

    @Null
    private BigInteger id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String registerNumber;

    @NotNull
    private BigInteger userId;

    @Null
    private UserDto user;

}
