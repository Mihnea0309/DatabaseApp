package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.ReparatiiPiese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparatiiPieseRepository extends JpaRepository<ReparatiiPiese, Integer> {
}
