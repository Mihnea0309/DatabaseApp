package com.BD.Service_Auto.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Reparatii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Masina", nullable = false)
    private Masini idMasina;

    private Date dataInceput;
    private Date dataFinalizare;
    private String descriere;
    private float costManopera;
    private float costTotal;

    public Reparatii() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Masini getIdMasina() {
        return idMasina;
    }

    public void setIdMasina(Masini idMasina) {
        this.idMasina = idMasina;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataFinalizare() {
        return dataFinalizare;
    }

    public void setDataFinalizare(Date dataFinalizare) {
        this.dataFinalizare = dataFinalizare;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public float getCostManopera() {
        return costManopera;
    }

    public void setCostManopera(float costManopera) {
        this.costManopera = costManopera;
    }

    public float getCostTotal() {
        return costTotal;
    }

    public void setCostTotal(float costTotal) {
        this.costTotal = costTotal;
    }
}
