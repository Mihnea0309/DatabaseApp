package com.BD.Service_Auto.model;

import jakarta.persistence.*;

import java.util.List;


//aceasta este clasa pentru tabela piese cu toate campurile ei
@Entity
public class Piese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nume;
    private float pret;
    private int stoc;

    @OneToMany(mappedBy = "idPiesa", fetch = FetchType.LAZY)
    private List<ReparatiiPiese> pieseReparatii;

    public Piese() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
}
