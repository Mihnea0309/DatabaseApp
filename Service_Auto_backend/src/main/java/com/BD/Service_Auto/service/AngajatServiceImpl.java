package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Angajati;
import com.BD.Service_Auto.repository.AngajatiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AngajatServiceImpl implements AngajatiService{

    @Autowired
    private AngajatiRepository angajatiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Angajati saveAngajati(Angajati angajati) {
        if (angajati.getParolaSite() != null) {
            angajati.setParolaSite(passwordEncoder.encode(angajati.getParolaSite()));
        }
        return angajatiRepository.save(angajati);
    }

    public boolean authenticate(String email, String parola) {
        Optional<Angajati> angajat = angajatiRepository.findByEmail(email);
        log.info("parola angajatului este " + angajat.get().getParolaSite() + " iar parola stocata este " + parola);
        if (angajat.isPresent()) {
            boolean isMatch = passwordEncoder.matches(parola, angajat.get().getParolaSite());
            log.info(String.valueOf(isMatch));
            return isMatch;
        }
        else{
            log.info("asdfgh");
            return false;
        }
    }

    @Override
    public String register(String email, String parola) {
        Optional<Angajati> angajat = angajatiRepository.findByEmail(email);
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
            if(digit && lowercase && uppercase) {
                angajat.get().setParolaSite(passwordEncoder.encode(parola));
                angajatiRepository.save(angajat.get());
                return "ok";
            }
            return "The password didnt match one of the conditions";
        }
        return "The email was not found";
    }
}
