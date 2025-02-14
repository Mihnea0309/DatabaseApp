package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.ReparatiiPiese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface ReparatiiPieseRepository extends JpaRepository<ReparatiiPiese, Integer> {

    //metoda care cauta in tabela ReparatiiPiese
    @Query(value = "select * from reparatii_piese", nativeQuery = true)
    List<ReparatiiPiese> getAllReparatiiPiese();

    //metoda care cuata piesele si cantitatea folosita
    @Query(value = "select p.nume, p.stoc, rp.cantitate, r.descriere " +
            "from reparatii_piese rp " +
            "join reparatii r on rp.id_reparatie = r.id " +
            "join piese p on p.id = rp.id_reparatie ", nativeQuery = true)
    List<Object[]> findPiecesAndQuantity();
}
