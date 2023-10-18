package com.cabinet.gestion.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String nom;
    private String prénom;
    private String adresse;
    private char sexe;
    private Date dateNaissance;
    private int téléphone;

    public Patient(Long code, String nom, String prénom, String adresse, char sexe, Date dateNaissance, int téléphone) {
        this.code = code;
        this.nom = nom;
        this.prénom = prénom;
        this.adresse = adresse;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.téléphone = téléphone;
    }

    public Patient() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getTéléphone() {
        return téléphone;
    }

    public void setTéléphone(int téléphone) {
        this.téléphone = téléphone;
    }
}
