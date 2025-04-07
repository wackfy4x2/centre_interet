package com.tutore.centre_interet.DTO;

import com.tutore.centre_interet.Entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

public class CentreDTO {

    private Long id;
    private String nom;
    private String description;
    private boolean statut;
    private List<Adresse> adresse;
    private List<Serviceent> serviceents;
    private Set<Contact> contacts;
    private String principaleimage;
    private Set<Image> images;
    private Categorie categorie;
    private Long iduser;

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getPrincipaleimage() {
        return principaleimage;
    }

    public void setPrincipaleimage(String principaleimage) {
        this.principaleimage = principaleimage;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Serviceent> getServiceents() {
        return serviceents;
    }

    public void setServiceents(List<Serviceent> serviceents) {
        this.serviceents = serviceents;
    }

    public List<Adresse> getAdresse() {
        return adresse;
    }

    public void setAdresse(List<Adresse> adresse) {
        this.adresse = adresse;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
