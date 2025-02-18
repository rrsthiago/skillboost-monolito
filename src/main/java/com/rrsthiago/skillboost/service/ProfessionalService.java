package com.rrsthiago.skillboost.service;

import com.rrsthiago.skillboost.exception.ResourceNotFoundException;
import com.rrsthiago.skillboost.exception.UserDoesntExistException;
import com.rrsthiago.skillboost.model.Professional;
import com.rrsthiago.skillboost.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private UserService userService;

    public Professional create(Professional professional) {
        try {
            userService.get(professional.getUser().getId());
        } catch (ResourceNotFoundException e) {
            throw new UserDoesntExistException(professional.getUser().getId());
        }

        return professionalRepository.save(professional);
    }

    public Professional get(BigInteger id) {
        return findProfessionalById(id);
    }

    public List<Professional> list() {
        return professionalRepository.findAll();
    }

    public Professional update(BigInteger id, Professional professional) {
        var existingProfessional = findProfessionalById(id);
        existingProfessional.setName(professional.getName());
        existingProfessional.setEmail(professional.getEmail());
        existingProfessional.setRegisterNumber(professional.getRegisterNumber());
        existingProfessional.setUser(professional.getUser());

        return professionalRepository.save(existingProfessional);
    }

    public void delete(BigInteger id) {
        professionalRepository.deleteById(id);
    }

    private Professional findProfessionalById(BigInteger id) {
        return professionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
