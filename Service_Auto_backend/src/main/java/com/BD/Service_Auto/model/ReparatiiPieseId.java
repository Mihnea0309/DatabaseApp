package com.BD.Service_Auto.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReparatiiPieseId implements Serializable {
    private int idReparatie;
    private int idPiesa;

    public ReparatiiPieseId() {
    }

    public ReparatiiPieseId(int idReparatie, int idPiesa) {
        this.idReparatie = idReparatie;
        this.idPiesa = idPiesa;
    }

    public int getIdReparatie() {
        return idReparatie;
    }

    public void setIdReparatie(int idReparatie) {
        this.idReparatie = idReparatie;
    }

    public int getIdPiesa() {
        return idPiesa;
    }

    public void setIdPiesa(int idPiesa) {
        this.idPiesa = idPiesa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReparatiiPieseId that = (ReparatiiPieseId) o;
        return idReparatie == that.idReparatie && idPiesa == that.idPiesa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReparatie, idPiesa);
    }
}
