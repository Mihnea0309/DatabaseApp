package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.ReparatiiAngajati;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.Date;
import java.util.List;

public interface ReparatiiAngajatiService {

    public List<ReparatiiAngajati> getAllReparatiiAngajati();

    public List<Object[]> findEmployeesByFilter(int dataFinalizare);

    public List<Object[]> findTotalHoursWorkedPerEmployee(Integer numarOre);
}
