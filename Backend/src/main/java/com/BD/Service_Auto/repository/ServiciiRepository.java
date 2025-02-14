package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.model.Servicii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface ServiciiRepository extends JpaRepository<Servicii, Integer> {

    //metoda care cauta servicii
    @Query(value = "select * from servicii", nativeQuery = true)
    List<Servicii> getAllServicii();

    //metoda care cauta servicii dupa id
    @Query(value = "select * from servicii where id = :id", nativeQuery = true)
    Optional<Servicii> findById(@Param("id") Integer id);

    //metoda care salveaza serviciile
    @Transactional
    @Modifying
    @Query(value = "insert into Servicii(tip, pret, durata) " +
            "VALUES (:tip, :pret, :durata)", nativeQuery = true)
    int saveServiciu(@Param("tip") String tip,
                     @Param("pret") Float pret,
                     @Param("durata") Time durata);


    //metoda care actualizeaza serviciile
    @Modifying
    @Transactional
    @Query(value = "update servicii set tip = :tip, pret = :pret, durata = :durata where id = :id", nativeQuery = true)
    int update(@Param("tip") String tip, @Param("pret") Float pret, @Param("durata") Time durata, @Param("id") Integer id);

    //metoda care sterge serviciile
    @Transactional
    @Modifying
    @Query(value = "delete from servicii where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);

}
