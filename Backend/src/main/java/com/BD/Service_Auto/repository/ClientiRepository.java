package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Clienti;
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
public interface ClientiRepository extends JpaRepository<Clienti, Integer> {

    //metoda care afiseaza clientii
    @Query(value = "select * from clienti", nativeQuery = true)
    List<Clienti> getAllClienti();

    //metoda care cauta clientii in functie de id
    @Query(value = "select * from clienti where id_client = :id_client", nativeQuery = true)
    Optional<Clienti> getClientById(@Param("id_client") Integer id);

    //metoda de inserare a clientilor
    @Modifying
    @Query(value = "insert into clienti(nume, prenume, adresa, telefon, email) VALUES(:nume, :prenume, :adresa, :telefon, :email)", nativeQuery = true)
    int saveClienti(@Param("nume") String nume,
                    @Param("prenume") String prenume,
                    @Param("adresa") String adresa,
                    @Param("telefon") String telefon,
                    @Param("email") String email);

    //metoda de actualizare a clientilor
    @Modifying
    @Transactional
    @Query(value = "update clienti set nume = :nume, prenume = :prenume, adresa = :adresa, email = :email, telefon = :telefon where id_client = :id_client", nativeQuery = true)
    int update(@Param("nume") String nume, @Param("prenume") String prenume, @Param("adresa") String adresa, @Param("email") String email, @Param("telefon") String telefon, @Param("id_client") Integer id);

    //metoda de stergere a clientilor
    @Modifying
    @Transactional
    @Query(value = "delete from clienti where id_client = :id_client", nativeQuery = true)
    void delete(@Param("id_client") Integer id);

    //o metoda care cauta clientii care nu au asigurare la masini
    @Query(value = "select c.nume, c.prenume, m.marca, m.model, m.asigurare " +
            "from clienti c " +
            "join masini m on m.id_client = c.id_client " +
            "where m.asigurare = :asigurare", nativeQuery = true)
    List<Object[]> findClientsWithoutInsurance(@Param("asigurare") String asigurare);

    //o metoda care cauta clientii in functie de costul reparatiei
    @Query(value = "select c.nume, c.prenume, m.marca, m.model, r.descriere, rp.pret_total " +
                   "from clienti c " +
                   "join masini m on m.id_client = c.id_client " +
                   "join reparatii r on m.id = r.id_masina " +
                   "join reparatii_piese rp on rp.id_reparatie = r.id ", nativeQuery = true)
    List<Object[]> findClientsByCost();

    //o metoda care cacuta clientii in functie de un cost introdus
    @Query(value = "select c.nume, c.prenume, r.cost_manopera " +
            "from clienti c, reparatii r " +
            "where r.cost_manopera > :cost_manopera " +
            "and exists (select 1 " +
            "from masini m " +
            "where r.id_masina = m.id " +
            "and m.id_client = c.id_client)",
            nativeQuery = true)
    List<Object[]> findClientsWithExpensiveRepairs(@Param("cost_manopera") Integer cost);


    //metoda de a cauta clientii dupa email
    @Query(value = "select * from clienti where email = :email", nativeQuery = true)
    Optional<Clienti> findByEmail(@Param("email") String email);
}
