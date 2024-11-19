package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Servicii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiciiRepository extends JpaRepository<Servicii, Integer> {
}
