package com.BD.Service_Auto.model;

import jakarta.persistence.*;

@Entity
public class Masini {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_Client", nullable = false)
    private Clienti idClient;
    private String marca;
    private String model;
    private String anFabricatie;
    private String numar_Inmatriculare;
    private String asigurare;
    private String serieSasiu;

    public Masini() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clienti getIdClient() {
        return idClient;
    }

    public void setIdClient(Clienti idClient) {
        this.idClient = idClient;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(String anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public String getNumar_Inmatriculare() {
        return numar_Inmatriculare;
    }

    public void setNumar_Inmatriculare(String numar_Inmatriculare) {
        this.numar_Inmatriculare = numar_Inmatriculare;
    }

    public String getAsigurare() {
        return asigurare;
    }

    public void setAsigurare(String asigurare) {
        this.asigurare = asigurare;
    }

    public String getSerieSasiu() {
        return serieSasiu;
    }

    public void setSerieSasiu(String serieSasiu) {
        this.serieSasiu = serieSasiu;
    }
}
