package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.ReparatiiAngajati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparatiiAngajatiRepository extends JpaRepository<ReparatiiAngajati, Integer> {
}
