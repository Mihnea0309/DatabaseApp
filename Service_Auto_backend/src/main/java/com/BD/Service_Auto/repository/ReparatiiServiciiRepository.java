package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.ReparatiiServicii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReparatiiServiciiRepository extends JpaRepository<ReparatiiServicii, Integer> {
}
