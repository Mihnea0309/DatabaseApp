package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Clienti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientiRepository extends JpaRepository<Clienti, Integer> {
}
