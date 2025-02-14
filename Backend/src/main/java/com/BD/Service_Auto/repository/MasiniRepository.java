package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.model.Masini;
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
public interface MasiniRepository extends JpaRepository<Masini, Integer> {

    //metoda care cauta masinile
    @Query(value = "select * from masini", nativeQuery = true)
    List<Masini> getAllMasini();

    //metoda care cauta masinile dupa id
    @Query(value = "select * from masini where id = :id", nativeQuery = true)
    Optional<Masini> getCarById(@Param("id") Integer id);

    //metoda care cauta masinile dupa numarul de inmatriculare
    @Query(value = "select * from masini where numar_inmatriculare = :numar_inmatriculare", nativeQuery = true)
    Optional<Masini> findByNrInmatriculare(@Param("numar_inmatriculare") String nrInmatriculare);

    //metoda care sterge masinile
    @Transactional
    @Modifying
    @Query(value = "delete from masini where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);

    //metoda care actualizeaza masinile
    @Modifying
    @Transactional
    @Query(value = "update masini set marca = :marca, model = :model, an_fabricatie = :anFabricatie, numar_inmatriculare = :numar_Inmatriculare , asigurare = :asigurare," +
            " serie_sasiu = :serieSasiu where id = :id", nativeQuery = true)
    int update(@Param("marca") String marca, @Param("model") String model, @Param("anFabricatie") String anFabricatie, @Param("numar_Inmatriculare") String numar_Inmatriculare,
               @Param("asigurare") String asigurare, @Param("serieSasiu") String serieSasiu, @Param("id") Integer id);

    //metoda care insereaza masinile
    @Modifying
    @Transactional
    @Query(value = "insert into Masini(marca, model, an_fabricatie, numar_inmatriculare, asigurare, serie_sasiu, id_client) " +
            "VALUES (:marca, :model, :anFabricatie, :numar_Inmatriculare, :asigurare, :serieSasiu, :id_client)", nativeQuery = true)
    int saveMasini(@Param("marca") String marca,
                     @Param("model") String model,
                     @Param("anFabricatie") String anFabricatie,
                     @Param("numar_Inmatriculare") String numar_Inmatriculare,
                     @Param("asigurare") String asigurare,
                     @Param("serieSasiu") String serieSasiu,
                     @Param("id_client") Integer id_client);

    //metoda care cauta masinile dupa pretul de reparatie
    @Query(value = "select m.marca, m.model, m.asigurare, r.descriere, rp.pret_total " +
            "from masini m " +
            "join reparatii r on m.id = r.id_masina " +
            "join reparatii_piese rp on r.id = rp.id_reparatie " +
            "where rp.pret_total >= :pretTotal", nativeQuery = true)
    List<Object[]> findCarsByRepairPrice(@Param("pretTotal") Float pret);

    //metoda care cauta masini care nu au fost reparate
    @Query(value = "select m.marca, m.model " +
            "from Masini m " +
            "where not exists (select 1 " +
            "from Reparatii r " +
            "where r.id_masina = m.id)", nativeQuery = true)
    List<Object[]> findCarsWithoutRepairs();

    //metoda care cauta proprietarii masinilor
    @Query(value = "select c.nume, c.prenume, m.marca, m.model " +
            "from Masini m " +
            "join Clienti c on m.id_client = c.id_client " +
            "where c.nume = :nume and c.prenume = :prenume ",
            nativeQuery = true)
    List<Object[]> findOwnerOfCars(@Param("nume") String nume, @Param("prenume") String prenume);

}
