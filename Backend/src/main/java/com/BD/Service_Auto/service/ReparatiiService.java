package com.BD.Service_Auto.service;


import com.BD.Service_Auto.model.Reparatii;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReparatiiService {

    public List<Reparatii> getAllReparatii();

    public Optional<Reparatii> getRepairById(Integer id);

    public int saveReparatie(Date dataInceput, Date dataFinalizare, String descriere, Float cost, String nrInmatriculare);

    public int update(String descriere, Float cost, Date dataInceput, Date dataFinalizare, Integer id);

    public void delete(Integer id);

    public List<Object[]> findRepairsOnRecentCars(Integer an);
}
