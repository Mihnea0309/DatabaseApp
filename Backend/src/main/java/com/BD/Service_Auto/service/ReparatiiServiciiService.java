package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.ReparatiiServicii;

import java.util.List;

public interface ReparatiiServiciiService {

    public List<ReparatiiServicii> getAllReparatiiServicii();

    public List<Object[]> getRepairsAndServices();
}
