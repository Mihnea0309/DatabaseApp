package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Angajati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AngajatiRepository extends JpaRepository<Angajati, Integer> {
    Optional<Angajati> findByEmail(String email);
}
