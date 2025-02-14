package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Clienti;
import com.BD.Service_Auto.model.Masini;
import com.BD.Service_Auto.model.Reparatii;
import com.BD.Service_Auto.model.ReparatiiPiese;
import com.BD.Service_Auto.repository.MasiniRepository;
import com.BD.Service_Auto.repository.ReparatiiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class ReparatiiServiceImpl implements ReparatiiService{

    @Autowired
    private ReparatiiRepository reparatiiRepository;

    @Autowired
    private MasiniRepository masiniRepository;

    @Override
    @Transactional
    public List<Reparatii> getAllReparatii(){
        return reparatiiRepository.getAllReparatii();
    }

    @Override
    public Optional<Reparatii> getRepairById(Integer id) {
        return reparatiiRepository.getRepairById(id);
    }

    @Override
    public int saveReparatie(Date dataInceput, Date dataFinalizare, String descriere, Float cost, String nrInmatriculare) {
        //verific daca exista masina in functie de numarul de inmatriculare ca sa adaug id ul ei in tabela reparatii
        Masini masina = masiniRepository.findByNrInmatriculare(nrInmatriculare)
                .orElseThrow(() -> new RuntimeException("Clientul cu email ul " + nrInmatriculare + " nu există"));
        return reparatiiRepository.saveReparatie(dataInceput, dataFinalizare, descriere, cost, masina.getId());
    }

    @Override
    public int update(String descriere, Float cost, Date dataInceput, Date dataFinalizare, Integer id) {
        return reparatiiRepository.update(descriere, cost, dataInceput, dataFinalizare, id);
    }

    @Override
    public void delete(Integer id) {
        reparatiiRepository.delete(id);
    }

    @Override
    public List<Object[]> findRepairsOnRecentCars(Integer an) {
        return reparatiiRepository.findRepairsOnRecentCars(an);
    }
}
