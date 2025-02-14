package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Servicii;
import com.BD.Service_Auto.repository.ServiciiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class ServiciiServiceImpl implements ServiciiService {

    @Autowired
    private ServiciiRepository serviciiRepository;

    @Override
    @Transactional
    public List<Servicii> getAllServicii(){
        return serviciiRepository.getAllServicii();
    }

    @Override
    public Optional<Servicii> getById(Integer id) {
        return serviciiRepository.findById(id);
    }

    @Override
    public int saveServiciu(String tip, Float pret, Time durata) {
        return serviciiRepository.saveServiciu(tip, pret, durata);
    }

    @Override
    public int update(String tip, Float pret, Time durata, Integer id) {
        return serviciiRepository.update(tip, pret, durata, id);
    }

    @Override
    public void delete(Integer id) {
        serviciiRepository.delete(id);
    }
}
