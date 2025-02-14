package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.ReparatiiServicii;
import com.BD.Service_Auto.repository.ReparatiiServiciiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class ReparatiiServiciiServiceImpl implements ReparatiiServiciiService{

    @Autowired
    private ReparatiiServiciiRepository reparatiiServiciiRepository;

    @Override
    @Transactional
    public List<ReparatiiServicii> getAllReparatiiServicii() {
        return reparatiiServiciiRepository.getAllReparatiiServicii();
    }

    @Override
    public List<Object[]> getRepairsAndServices() {
        return reparatiiServiciiRepository.getRepairsAndServices();
    }
}
