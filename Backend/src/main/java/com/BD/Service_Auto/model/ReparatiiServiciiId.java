package com.BD.Service_Auto.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

//aceasta este clasa care realizeaza legatura dintre primary key-uri
@Embeddable
public class ReparatiiServiciiId implements Serializable {

    private int idReparatie;
    private int idServiciu;

    public ReparatiiServiciiId() {
    }

    public ReparatiiServiciiId(int idReparatie, int idServiciu) {
        this.idReparatie = idReparatie;
        this.idServiciu = idServiciu;
    }

    public int getIdReparatie() {
        return idReparatie;
    }

    public void setIdReparatie(int idReparatie) {
        this.idReparatie = idReparatie;
    }

    public int getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(int idServiciu) {
        this.idServiciu = idServiciu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReparatiiServiciiId that = (ReparatiiServiciiId) o;
        return idReparatie == that.idReparatie && idServiciu == that.idServiciu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReparatie, idServiciu);
    }
}
