package com.BD.Service_Auto.repository;

import com.BD.Service_Auto.model.Angajati;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


//aceasta este clasa de repository care face legatura cu baza de date
@Repository
public interface AngajatiRepository extends JpaRepository<Angajati, Integer> {

    //metoda de afisare a tuturor angajatilor
    @Query(value = "select * from angajati", nativeQuery = true)
    List<Angajati> findAllAngajati();

    //metoda de gasire a angajatilor dupa email
    @Query(value = "select * from angajati where email = :email", nativeQuery = true)
    Optional<Angajati> findByEmail(@Param("email") String email);

    //metoda de gasire a angajatilor dupa id
    @Query(value = "select * from angajati where id = :id", nativeQuery = true)
    Optional<Angajati> findById(@Param("id") Integer id);

    //metoda de inserare a angajatilor
    @Modifying
    @Query(value = "insert into Angajati(nume, prenume, cnp, email, adresa, numartelefon, functie, salariu, parolasite) " +
            "VALUES (:nume, :prenume, :cnp, :email, :adresa, :numartelefon, :functie, :salariu, :parolasite)", nativeQuery = true)
    int saveAngajati(@Param("nume") String nume,
                     @Param("prenume") String prenume,
                     @Param("cnp") String cnp,
                     @Param("email") String email,
                     @Param("adresa") String adresa,
                     @Param("numartelefon") String numartelefon,
                     @Param("functie") String functie,
                     @Param("salariu") Float salariu,
                     @Param("parolasite") String parolasite);

    //metoda de actualizare a parolei unui angajat
    @Transactional
    @Modifying
    @Query(value = "update angajati set parolasite = :parola where email = :email", nativeQuery = true)
    int updatePassword(@Param("parola") String parola, @Param("email") String email);

    //metoda de actualizare a angajatilor
    @Modifying
    @Transactional
    @Query(value = "update angajati set nume = :nume, prenume = :prenume, cnp = :cnp, email = :email , adresa = :adresa," +
            " functie = :functie, salariu = :salariu, numartelefon = :numartelefon where id = :id", nativeQuery = true)
    int update(@Param("nume") String nume, @Param("prenume") String prenume, @Param("cnp") String cnp, @Param("email") String email,
               @Param("adresa") String adresa, @Param("functie") String functie, @Param("salariu") Float salariu,
               @Param("numartelefon") String numartelefon, @Param("id") Integer id);

    //metoda de stergere a unui angajat in functie de id
    @Modifying
    @Transactional
    @Query(value = "delete from angajati where id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);

    //o metoda care cauta angajatii care nu lucreaza la reparatii
    @Query(value = "select a.nume, a.prenume, a.functie, a.salariu " +
            "from angajati a " +
            "where a.salariu > :salariu " +
            "and not exists (select 1 " +
            "from reparatii_angajati ra " +
            "where ra.id_angajat = a.id)",
            nativeQuery = true)
    List<Object[]> findEmployeesWithoutRepairs(@Param("salariu") Integer salariu);

}
