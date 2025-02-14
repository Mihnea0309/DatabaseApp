package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.ReparatiiAngajati;
import com.BD.Service_Auto.repository.ReparatiiAngajatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.Date;
import java.util.List;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class ReparatiiAngajatiServiceImpl implements ReparatiiAngajatiService{

    @Autowired
    private ReparatiiAngajatiRepository reparatiiAngajatiRepository;

    @Transactional
    public List<ReparatiiAngajati> getAllReparatiiAngajati(){
        return reparatiiAngajatiRepository.getAllReparatiiAngajati();
    }

    @Override
    public List<Object[]> findEmployeesByFilter(int dataFinalizare) {
        return reparatiiAngajatiRepository.findEmployeesByFilter(dataFinalizare);
    }

    @Override
    public List<Object[]> findTotalHoursWorkedPerEmployee(Integer numarOre) {
        return reparatiiAngajatiRepository.findTotalHoursWorkedPerEmployee(numarOre);
    }
}
