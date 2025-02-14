package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Masini;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MasiniService {

    public List<Masini> getAllMasini();

    public Optional<Masini> getCarById(Integer id);

    public void delete(Integer id);

    public int update(String marca, String model, String anFabricatie, String numar_Inmatriculare, String asigurare, String serieSasiu, Integer id);

    public int saveMasini(String marca, String model, String anFabricatie, String numar_Inmatriculare, String asigurare, String serieSasiu, String email);

    public List<Object[]> findCarsByRepairPrice(Float pret);

    public List<Object[]> findCarsWithoutRepairs();

   public List<Object[]> findOwnerOfCars(String nume, String prenume);
}
