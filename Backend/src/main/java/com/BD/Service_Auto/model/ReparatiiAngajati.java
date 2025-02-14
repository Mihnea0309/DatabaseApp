package com.BD.Service_Auto.model;

import jakarta.persistence.*;


//aceasta este clasa pentru tabela de legatura angajati-reparatii cu toate campurile ei
@Entity
@Table(name = "reparatii_angajati")
public class ReparatiiAngajati {
    @EmbeddedId
    private ReparatiiAngajatiId id;

    @ManyToOne
    @MapsId("idReparatie")
    @JoinColumn(name = "ID_Reparatie", nullable = false)
    private Reparatii idReparatie;

    @ManyToOne
    @MapsId("idAngajat")
    @JoinColumn(name = "ID_Angajat", nullable = false)
    private Angajati idAngajat;

    private int numarOre;

    public ReparatiiAngajati() {
    }

    public ReparatiiAngajati(ReparatiiAngajatiId id, Reparatii idReparatie, Angajati idAngajat, int numarOre) {
        this.id = id;
        this.idReparatie = idReparatie;
        this.idAngajat = idAngajat;
        this.numarOre = numarOre;
    }

    public ReparatiiAngajatiId getId() {
        return id;
    }

    public void setId(ReparatiiAngajatiId id) {
        this.id = id;
    }

    public Reparatii getIdReparatie() {
        return idReparatie;
    }

    public void setIdReparatie(Reparatii idReparatie) {
        this.idReparatie = idReparatie;
    }

    public Angajati getIdAngajat() {
        return idAngajat;
    }

    public void setIdAngajat(Angajati idAngajat) {
        this.idAngajat = idAngajat;
    }

    public int getNumarOre() {
        return numarOre;
    }

    public void setNumarOre(int numarOre) {
        this.numarOre = numarOre;
    }
}
