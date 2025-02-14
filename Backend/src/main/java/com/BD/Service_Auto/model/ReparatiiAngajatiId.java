package com.BD.Service_Auto.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

//aceasta este clasa care realizeaza legatura dintre primary key-uri
@Embeddable
public class ReparatiiAngajatiId implements Serializable {

    private int idReparatie;
    private int idAngajat;

    public ReparatiiAngajatiId() {
    }

    public ReparatiiAngajatiId(int idReparatie, int idAnagajat) {
        this.idReparatie = idReparatie;
        this.idAngajat = idAnagajat;
    }

    public int getIdReparatie() {
        return idReparatie;
    }

    public void setIdReparatie(int idReparatie) {
        this.idReparatie = idReparatie;
    }

    public int getIdAnagajat() {
        return idAngajat;
    }

    public void setIdAnagajat(int idAnagajat) {
        this.idAngajat = idAnagajat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReparatiiAngajatiId that = (ReparatiiAngajatiId) o;
        return idReparatie == that.idReparatie && idAngajat == that.idAngajat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReparatie, idAngajat);
    }
}
