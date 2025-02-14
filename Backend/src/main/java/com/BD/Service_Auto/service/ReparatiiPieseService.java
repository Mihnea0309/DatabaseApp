package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.ReparatiiPiese;

import java.util.List;

public interface ReparatiiPieseService {

    public List<ReparatiiPiese> getAllReparatiiPiese();

    public List<Object[]> getPieseAndQuantity();
}
