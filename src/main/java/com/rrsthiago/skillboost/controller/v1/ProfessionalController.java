package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.ProfessionalMapper;
import com.rrsthiago.skillboost.dto.ProfessionalDto;
import com.rrsthiago.skillboost.service.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class ProfessionalController {

    private static final String PATH = "/v1/professionals";
    private static final String ID = "id";
    private static final String PATH_ID = "/v1/professionals/{id}";

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping(PATH)
    public ResponseEntity<?> list() {
        var professionals = professionalService.list();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professionalMapper.modelsToDtos(professionals));
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<?> get(@PathVariable(ID) BigInteger id) {
        var professional = professionalService.get(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professionalMapper.modelToDto(professional));
    }

    @PostMapping(PATH)
    public ResponseEntity<?> create(@RequestBody @Valid ProfessionalDto dto) {
        var professional = professionalService.create(professionalMapper.dtoToModel(dto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professionalMapper.modelToDto(professional));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<?> update(@PathVariable(ID) BigInteger id,
                                    @RequestBody @Valid ProfessionalDto dto) {
        var professional = professionalService.update(id, professionalMapper.dtoToModel(dto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(professionalMapper.modelToDto(professional));
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<?> delete(@PathVariable(ID) BigInteger id) {
        professionalService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
