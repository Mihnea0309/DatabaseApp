package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.ReparatiiPiese;
import com.BD.Service_Auto.repository.ReparatiiPieseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//clasa care implementeaza metodele pentru a structura regulile si operatiunile aplicatiei
@Service
public class ReparatiiPieseServiceImpl implements ReparatiiPieseService{

    @Autowired
    private ReparatiiPieseRepository reparatiiPieseRepository;

    @Override
    @Transactional
    public List<ReparatiiPiese> getAllReparatiiPiese() {
        return reparatiiPieseRepository.getAllReparatiiPiese();
    }

    @Override
    public List<Object[]> getPieseAndQuantity() {
        return reparatiiPieseRepository.findPiecesAndQuantity();
    }
}
