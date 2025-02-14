package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.ReparatiiAngajati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.Date;
import java.util.List;

//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface ReparatiiAngajatiRepository extends JpaRepository<ReparatiiAngajati, Integer> {

    //metoda care cauta in tabela ReparatiiAngajati
    @Query(value = "select * from reparatii_angajati", nativeQuery = true)
    List<ReparatiiAngajati> getAllReparatiiAngajati();

    //metoda care cauta in tabela ReparatiiAngajati dupa anul in care s-a terminat reparatia
    @Query(value = "select a.nume, a.prenume, r.data_finalizare " +
            "from reparatii_angajati ra " +
            "join angajati a on ra.id_angajat = a.id " +
            "join reparatii r on ra.id_reparatie = r.id " +
            "where extract(year from r.data_finalizare) < :an_finalizare", nativeQuery = true)
    List<Object[]> findEmployeesByFilter(@Param("an_finalizare") int dataFinalizare);

    //metoda care cauta cate ore au lucrat angajatii in total
    @Query(value = "select a.nume, a.prenume, a.salariu, SUM(ra.numar_ore) as totalOreLucrate " +
            "from reparatii_angajati ra " +
            "join angajati a on ra.id_angajat = a.id " +
            "group by a.nume, a.prenume, a.salariu " +
            "having SUM(ra.numar_ore) > :numarOre", nativeQuery = true)
    List<Object[]> findTotalHoursWorkedPerEmployee(@Param("numarOre") Integer numarOre);

}
