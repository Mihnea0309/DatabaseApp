package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Piese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PieseRepository extends JpaRepository<Piese, Integer> {
}
