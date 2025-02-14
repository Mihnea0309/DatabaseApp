package com.BD.Service_Auto.model;

import jakarta.persistence.*;

//aceasta este clasa pentru tabela de legatura servicii-reparatii cu toate campurile ei
@Entity
public class ReparatiiServicii {

    @EmbeddedId
    private ReparatiiServiciiId id;

    @ManyToOne
    @MapsId("idReparatie")
    @JoinColumn(name = "ID_Reparatie", nullable = false)
    private Reparatii idReparatie;

    @ManyToOne
    @MapsId("idServiciu")
    @JoinColumn(name = "ID_Serviciu", nullable = false)
    private Servicii idServiciu;
    private float pretTotal;

    public ReparatiiServicii() {
    }

    public ReparatiiServicii(ReparatiiServiciiId id, Reparatii idReparatie, Servicii idServiciu, int cantitate, float pretTotal) {
        this.id = id;
        this.idReparatie = idReparatie;
        this.idServiciu = idServiciu;
        this.pretTotal = pretTotal;
    }

    public ReparatiiServiciiId getId() {
        return id;
    }

    public void setId(ReparatiiServiciiId id) {
        this.id = id;
    }

    public Reparatii getIdReparatie() {
        return idReparatie;
    }

    public void setIdReparatie(Reparatii idReparatie) {
        this.idReparatie = idReparatie;
    }

    public Servicii getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(Servicii idServiciu) {
        this.idServiciu = idServiciu;
    }

    public float getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(float pretTotal) {
        this.pretTotal = pretTotal;
    }
}
