package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.model.Piese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface PieseRepository extends JpaRepository<Piese, Integer> {

    //metoda care cauta piesele
    @Query(value = "select * from piese", nativeQuery = true)
    List<Piese> getAllPiese();

    //metoda care cauta piesele in functie de id
    @Query(value = "select * from piese where id = :id", nativeQuery = true)
    Optional<Piese> getPieseById(@Param("id") Integer id);

    //metoda care sterge piesele
    @Modifying
    @Transactional
    @Query(value = "delete from piese where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);

    //metoda care salveaza piesele
    @Modifying
    @Transactional
    @Query(value = "insert into piese(nume, pret, stoc) " +
            "VALUES (:nume, :pret, :stoc)", nativeQuery = true)
    int savePiese(@Param("nume") String nume,
                   @Param("pret") Float pret,
                   @Param("stoc") Integer stoc);

    //metoda care actualizeaza piesele
    @Modifying
    @Transactional
    @Query(value = "update piese set nume = :nume, pret = :pret, stoc = :stoc where id = :id", nativeQuery = true)
    int update(@Param("nume") String nume, @Param("pret") Float pret, @Param("stoc") Integer stoc, @Param("id") Integer id);
}
