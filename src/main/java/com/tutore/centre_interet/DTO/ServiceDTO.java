package com.tutore.centre_interet.DTO;

import com.tutore.centre_interet.Entities.Manifestation;

import java.util.Set;

public class ServiceDTO {

    private Long id;
    private String nom;
    private String description;
    public boolean statut;
    public double prix;
    private Set<Manifestation> manifestations;


    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isStatut() {
        return this.statut;
    }

    public double getPrix() {
        return this.prix;
    }

    public Set<Manifestation> getManifestations() {
        return this.manifestations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setManifestations(Set<Manifestation> manifestations) {
        this.manifestations = manifestations;
    }
}
