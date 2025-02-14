package com.BD.Service_Auto.model;

import jakarta.persistence.*;

import java.util.List;

//aceasta este clasa pentru tabela angajati cu toate campurile ei
@Entity
public class Angajati {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nume;
    private String prenume;
    private String cnp;
    private String email;
    private String adresa;

    @Column(name = "numartelefon")
    private String numartelefon;
    private String functie;
    private float salariu;
    private String parolasite;

    @OneToMany(mappedBy = "idAngajat", fetch = FetchType.LAZY)
    private List<ReparatiiAngajati> angajatReparatii;


    public Angajati() {
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

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNumarTelefon() {
        return numartelefon;
    }

    public void setNumarTelefon(String numartelefon) {
        this.numartelefon = numartelefon;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    public String getParolaSite() {
        return parolasite;
    }

    public void setParolaSite(String parolasite) {
        this.parolasite = parolasite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
