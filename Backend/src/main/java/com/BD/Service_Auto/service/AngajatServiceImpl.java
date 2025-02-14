package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.repository.AngajatiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
@Slf4j
public class AngajatServiceImpl implements AngajatiService{

    @Autowired
    private AngajatiRepository angajatiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Angajati> getAllAngajati(){
        return angajatiRepository.findAllAngajati();
    }

    @Override
    public Optional<Angajati> findById(Integer id) {
        return angajatiRepository.findById(id);
    }

    @Override
    @Transactional
    public int saveAngajati(String nume, String prenume, String cnp, String email, String adresa,
                            String numartelefon, String functie, Float salariu, String parolasite) {
        //verificare daca parola exista ca sa o cripteze, intrucat pot adauga un angajat si daca nu i-am oferit o parola initial
        if (parolasite != null) {
            parolasite = passwordEncoder.encode(parolasite);
        }
        return angajatiRepository.saveAngajati(nume, prenume, cnp, email, adresa, numartelefon, functie, salariu, parolasite);
    }

    //logica pentru autentificarea unui angajat
    public boolean authenticate(String email, String parola) {
        //caut daca exista angajatul in functie de email ul primit
        Optional<Angajati> angajat = angajatiRepository.findByEmail(email);
        log.info("parola angajatului este " + angajat.get().getParolaSite() + " iar parola stocata este " + parola);

        //daca il gasesc, verific parola cu cea criptata
        if (angajat.isPresent()) {
            boolean isMatch = passwordEncoder.matches(parola, angajat.get().getParolaSite());
            log.info(String.valueOf(isMatch));
            return isMatch;
        }
        else{
            return false;
        }
    }

    //metoda pentru inregistrarea unui angajat
    @Override
    public String register(String email, String parola) {
        //caut angajatul in functie de email
        Optional<Angajati> angajat = angajatiRepository.findByEmail(email);

        //daca il gasesc, verific daca parola introdusa contine cerintele impuse
        if (angajat.isPresent()) {

            boolean uppercase = false, lowercase = false, digit = false;
            for(int i=0; i<parola.length(); i++)
            {
                if(Character.isDigit(parola.charAt(i)))
                    digit = true;
                else if(Character.isLowerCase(parola.charAt(i)))
                    lowercase = true;
                else if(Character.isUpperCase(parola.charAt(i)))
                    uppercase = true;
            }
            //daca parola este acceptate, o criptez si o salvez
            if(digit && lowercase && uppercase) {
                parola = passwordEncoder.encode(parola);
                angajatiRepository.updatePassword(parola, email);
                return "ok";
            }
            return "The password didnt match one of the conditions";
        }
        //daca nu gasesc angajatul, returnez ca nu am gasit un email
        return "The email was not found";
    }

    @Override
    public int update(String nume, String prenume, String cnp, String email, String adresa, String functie, Float salariu, String numartelefon, Integer id) {
        return angajatiRepository.update(nume, prenume, cnp, email, adresa, functie, salariu, numartelefon, id);
    }


    @Override
    public void delete(Integer id) {
        angajatiRepository.delete(id);
    }

    @Override
    public List<Object[]> findEmployeesWithoutRepairs(Integer salariu) {
        return angajatiRepository.findEmployeesWithoutRepairs(salariu);
    }
}
