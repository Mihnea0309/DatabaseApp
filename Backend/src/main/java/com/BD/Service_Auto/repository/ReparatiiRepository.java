package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.model.Masini;
import com.BD.Service_Auto.model.Reparatii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface ReparatiiRepository extends JpaRepository<Reparatii, Integer> {

    //metoda care cauta reparatiile
    @Query(value = "select * from reparatii", nativeQuery = true)
    List<Reparatii> getAllReparatii();

    //metoda care cauta reparatiile in functie de id
    @Query(value = "select * from reparatii where id = :id", nativeQuery = true)
    Optional<Reparatii> getRepairById(@Param("id") Integer id);

    //metoda care adauga reparatii
    @Transactional
    @Modifying
    @Query(value = "insert into reparatii(data_inceput, data_finalizare, descriere, cost_manopera, id_masina) " +
            "VALUES (:data_inceput, :data_finalizare, :descriere, :cost, :id_masina)", nativeQuery = true)
    int saveReparatie(@Param("data_inceput") Date dataInceput,
                     @Param("data_finalizare") Date dataFinalizare,
                     @Param("descriere") String descriere,
                     @Param("cost") Float cost,
                      @Param("id_masina") Integer idMasina);

    //metoda care actualizeaza reparatii
    @Modifying
    @Transactional
    @Query(value = "update reparatii set descriere = :descriere, cost_manopera = :cost_manopera, data_inceput = :data_inceput, data_finalizare = :data_finalizare" +
            " where id = :id", nativeQuery = true)
    int update(@Param("descriere") String descriere,
               @Param("cost_manopera") Float cost,
               @Param("data_inceput") Date dataInceput,
               @Param("data_finalizare") Date dataFinalizare,
               @Param("id") Integer id);

    //metoda care sterge reparatii
    @Transactional
    @Modifying
    @Query(value = "delete from reparatii where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);

    //metoda care cauta reparatii in functie de anul in care au fost fabricate masinile
    @Query(value = "select r.descriere, r.cost_manopera " +
            "from reparatii r " +
            "where exists (select 1 " +
            "from masini m " +
            "where r.id_masina = m.id and CAST(m.an_fabricatie AS integer) > :an_fabricatie)",
            nativeQuery = true)
    List<Object[]> findRepairsOnRecentCars(@Param("an_fabricatie") Integer an);


}
