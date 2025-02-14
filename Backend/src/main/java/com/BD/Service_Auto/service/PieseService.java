package com.BD.Service_Auto.service;

import com.BD.Service_Auto.model.Piese;

import java.util.List;
import java.util.Optional;

public interface PieseService {

    public List<Piese> getAllPiese();

    public Optional<Piese> getPieseById(Integer id);

    public void delete(Integer id);

    public int savePiese(String nume, Float pret, Integer stoc);

    public int update(String nume, Float pret, Integer stoc, Integer id);
}
