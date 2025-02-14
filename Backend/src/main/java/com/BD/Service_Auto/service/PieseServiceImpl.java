package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Piese;
import com.BD.Service_Auto.repository.PieseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class PieseServiceImpl implements PieseService{

    @Autowired
    private PieseRepository pieseRepository;

    @Override
    @Transactional
    public List<Piese> getAllPiese(){
        return pieseRepository.getAllPiese();
    }

    @Override
    public Optional<Piese> getPieseById(Integer id) {
        return pieseRepository.getPieseById(id);
    }

    @Override
    public void delete(Integer id) {
        pieseRepository.delete(id);
    }

    @Override
    public int savePiese(String nume, Float pret, Integer stoc) {
        return pieseRepository.savePiese(nume, pret, stoc);
    }

    @Override
    public int update(String nume, Float pret, Integer stoc, Integer id) {
        return pieseRepository.update(nume, pret, stoc, id);
    }
}
