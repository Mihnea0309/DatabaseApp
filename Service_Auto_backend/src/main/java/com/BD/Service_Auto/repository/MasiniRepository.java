package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Masini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasiniRepository extends JpaRepository<Masini, Integer> {
}
