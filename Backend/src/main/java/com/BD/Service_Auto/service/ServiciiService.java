package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Servicii;

import javax.swing.plaf.OptionPaneUI;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ServiciiService {

    public List<Servicii> getAllServicii();

    public Optional<Servicii> getById(Integer id);

    public int saveServiciu(String tip, Float pret, Time durata);

    public int update(String tip, Float pret, Time durata, Integer id);

    public void delete(Integer id);
}
