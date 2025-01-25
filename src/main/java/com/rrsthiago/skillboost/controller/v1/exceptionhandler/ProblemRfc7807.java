package com.rrsthiago.skillboost.controller.v1.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@JsonInclude(NON_NULL)
public class ProblemRfc7807 {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;
    private List<FieldProblem> objects;

    @Getter
    @Builder
    public static class FieldProblem {

        private String name;
        private String userMessage;

    }

}
