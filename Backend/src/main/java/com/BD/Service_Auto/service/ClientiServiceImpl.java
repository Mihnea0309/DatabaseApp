package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.repository.ClientiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class ClientiServiceImpl implements ClientiService{

    @Autowired
    private ClientiRepository clientiRepository;
    @Override
    @Transactional
    public int saveClienti(String nume, String prenume, String adresa, String telefon, String email) {
        return clientiRepository.saveClienti(nume, prenume, adresa, telefon, email);
    }

    @Override
    public Optional<Clienti> getClientById(Integer id) {
        return clientiRepository.getClientById(id);
    }

    @Override
    public List<Clienti> getAllClients() {
        return clientiRepository.getAllClienti();
    }

    @Override
    public int update(String nume, String prenume, String adresa, String email, String telefon, Integer id) {
        return clientiRepository.update(nume , prenume, adresa, email, telefon, id);
    }

    @Override
    public void delete(Integer id) {
        clientiRepository.delete(id);
    }

    @Override
    public List<Object[]> findClientsByCost() {
        return clientiRepository.findClientsByCost();
    }

    @Override
    public List<Object[]> findClientsWithoutInsurance(String asigurare) {
        return clientiRepository.findClientsWithoutInsurance(asigurare);
    }

    @Override
    public List<Object[]> findClientsByExpensiveCost(Integer cost) {
        return clientiRepository.findClientsWithExpensiveRepairs(cost);
    }
}
