package com.rrsthiago.skillboost.controller.v1;

import com.rrsthiago.skillboost.controller.v1.mapper.ProfileMapper;
import com.rrsthiago.skillboost.dto.ProfileDto;
import com.rrsthiago.skillboost.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class ProfileController {

    private static final String PATH = "/v1/profiles";
    private static final String ID = "id";
    private static final String PATH_ID = "/v1/profiles/{id}";

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private ProfileService profileService;

    @GetMapping(PATH)
    public ResponseEntity<?> list() {
        var profiles = profileService.list();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profiles);
    }

    @GetMapping(PATH_ID)
    public ResponseEntity<?> get(@PathVariable(ID) BigInteger id) {
        var profile = profileService.get(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profile);
    }

    @PostMapping(PATH)
    public ResponseEntity<?> create(@RequestBody @Valid ProfileDto profile) {
        var createdProfile = profileService.create(profileMapper.dtoToModel(profile));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profileMapper.modelToDto(createdProfile));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<?> update(@PathVariable(ID) BigInteger id,
                                    @RequestBody @Valid ProfileDto profile) {
        var updatedProfile = profileService.update(id, profileMapper.dtoToModel(profile));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(profileMapper.modelToDto(updatedProfile));
    }

    @DeleteMapping(PATH_ID)
    public ResponseEntity<?> delete(@PathVariable(ID) BigInteger id) {
        profileService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
