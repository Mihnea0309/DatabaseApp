package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Reparatii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparatiiRepository extends JpaRepository<Reparatii, Integer> {
}
