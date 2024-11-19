package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Angajati;

public interface AngajatiService {
    public Angajati saveAngajati(Angajati angajati);

    public boolean authenticate(String email, String parola);

    public String register(String email, String parola);

//    public Angajati setParola(String parolaSite);
}
