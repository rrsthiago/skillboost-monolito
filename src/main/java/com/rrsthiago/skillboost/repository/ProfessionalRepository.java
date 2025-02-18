package com.rrsthiago.skillboost.repository;

import com.rrsthiago.skillboost.model.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, BigInteger> {
}
