package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Angajati;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AngajatiService {

    public List<Angajati> getAllAngajati();

    public Optional<Angajati> findById(Integer id);

    public int saveAngajati(String nume, String prenume, String cnp, String email, String adresa,
                            String numartelefon, String functie, Float salariu, String parolasite);

    public boolean authenticate(String email, String parola);

    public String register(String email, String parola);

    public int update(String nume, String prenume, String cnp, String email, String adresa, String functie, Float salariu, String numartelefon, Integer id);

    public void delete(Integer id);


    public List<Object[]> findEmployeesWithoutRepairs(Integer salariu);
}
