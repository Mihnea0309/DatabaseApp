package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Clienti;

import java.util.List;
import java.util.Optional;

public interface ClientiService {

    public List<Clienti> getAllClients();

    public Optional<Clienti> getClientById(Integer id);

    public int saveClienti(String nume, String prenume, String adresa, String telefon, String email);

    public int update(String nume, String prenume, String adresa, String email, String telefon, Integer id);

    public void delete(Integer id);

    public List<Object[]> findClientsWithoutInsurance(String asigurare);

    public List<Object[]> findClientsByCost();

    public List<Object[]> findClientsByExpensiveCost(Integer cost);
}
