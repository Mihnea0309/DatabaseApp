package com.BD.Service_Auto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Clienti")
public class Clienti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Integer id_client;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "email")
    private String email;

    public Clienti() {
    }

    public Clienti(Integer id_client, String nume, String prenume, String adresa, String telefon, String email) {
        this.id_client = id_client;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
    }

    public int getId() {
        return id_client;
    }

    public void setId(int id) {
        this.id_client = id;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Clienti{" +
                "id_client=" + id_client +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
