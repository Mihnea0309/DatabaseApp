package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.model.Masini;
import com.BD.Service_Auto.repository.ClientiRepository;
import com.BD.Service_Auto.repository.MasiniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class MasiniServiceImpl implements MasiniService{

    @Autowired
    private MasiniRepository masiniRepository;

    @Autowired
    private ClientiRepository clientiRepository;

    @Override
    @Transactional
    public List<Masini> getAllMasini(){
        return masiniRepository.getAllMasini();
    }

    @Override
    public Optional<Masini> getCarById(Integer id) {
        return masiniRepository.getCarById(id);
    }

    @Override
    public void delete(Integer id) {
        masiniRepository.delete(id);
    }

    @Override
    public int update(String marca, String model, String anFabricatie, String numar_Inmatriculare, String asigurare, String serieSasiu, Integer id) {
        return masiniRepository.update(marca, model, anFabricatie, numar_Inmatriculare, asigurare, serieSasiu, id);
    }

    @Override
    public int saveMasini(String marca, String model, String anFabricatie, String numar_Inmatriculare, String asigurare, String serieSasiu, String email) {
        //caut daca exista clientul in functie de email ul introdus ca sa introduc id ul lui in tabela masini
        Clienti client = clientiRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Clientul cu email ul " + email + " nu există"));
        return masiniRepository.saveMasini(marca, model, anFabricatie, numar_Inmatriculare, asigurare, serieSasiu, client.getId());
    }

    @Override
    public List<Object[]> findCarsByRepairPrice(Float pret) {
        return masiniRepository.findCarsByRepairPrice(pret);
    }

    @Override
    public List<Object[]> findCarsWithoutRepairs() {
        return masiniRepository.findCarsWithoutRepairs();
    }

    @Override
    public List<Object[]> findOwnerOfCars(String nume, String prenume) {
        return masiniRepository.findOwnerOfCars(nume, prenume);
    }
}
