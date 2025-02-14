package com.BD.Service_Auto.model;

import jakarta.persistence.*;

//aceasta este clasa pentru tabela de legatura piese-reparatii cu toate campurile ei
@Entity
public class ReparatiiPiese {

    @EmbeddedId
    private ReparatiiPieseId id;

    @ManyToOne
    @MapsId("idReparatie")
    @JoinColumn(name = "ID_Reparatie", nullable = false)
    private Reparatii idReparatie;

    @ManyToOne
    @MapsId("idPiesa")
    @JoinColumn(name = "ID_Piesa", nullable = false)
    private Piese idPiesa;

    private int cantitate;

    private float pretTotal;

    public ReparatiiPiese() {
    }

    public ReparatiiPiese(ReparatiiPieseId id, Reparatii idReparatie, Piese idPiesa, int cantitate, float pretTotal) {
        this.id = id;
        this.idReparatie = idReparatie;
        this.idPiesa = idPiesa;
        this.cantitate = cantitate;
        this.pretTotal = pretTotal;
    }

    public ReparatiiPieseId getId() {
        return id;
    }

    public void setId(ReparatiiPieseId id) {
        this.id = id;
    }

    public Reparatii getIdReparatie() {
        return idReparatie;
    }

    public void setIdReparatie(Reparatii idReparatie) {
        this.idReparatie = idReparatie;
    }

    public Piese getIdPiesa() {
        return idPiesa;
    }

    public void setIdPiesa(Piese idPiesa) {
        this.idPiesa = idPiesa;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public float getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(float pretTotal) {
        this.pretTotal = pretTotal;
    }
}
