package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.ReparatiiServicii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface ReparatiiServiciiRepository extends JpaRepository<ReparatiiServicii, Integer> {

    //metoda care cauta in tabela ReparatiiServicii
    @Query(value = "select * from reparatii_servicii", nativeQuery = true)
    List<ReparatiiServicii> getAllReparatiiServicii();


    //metoda care cauta reparatiile si serviciile facute pe o masina
    @Query(value = "select m.marca, m.model, r.descriere, s.tip, rs.pret_total " +
            "from reparatii_servicii rs " +
            "join reparatii r on rs.id_reparatie = r.id " +
            "join servicii s on rs.id_serviciu = s.id " +
            "join masini m on r.id_masina = m.id ", nativeQuery = true)
    List<Object[]> getRepairsAndServices();
}
