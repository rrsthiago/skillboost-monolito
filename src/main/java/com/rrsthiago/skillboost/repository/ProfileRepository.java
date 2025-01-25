package com.rrsthiago.skillboost.repository;

import com.rrsthiago.skillboost.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, BigInteger> {
}
