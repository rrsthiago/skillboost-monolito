package com.rrsthiago.skillboost.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserDto {

    @Null
    private BigInteger id;

    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password; //Aplicação para fins didáticos. Nunca lidar com senhas dessa forma!

    @NotNull
    private BigInteger profileId;

    @Null
    private ProfileDto profile;

}
