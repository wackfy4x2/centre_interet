package com.tutore.centre_interet.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
public class CentreInteret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nom;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private Long iduser;
    private boolean statut;
    private Date created_at;
    private Date updated_at; 
    @OneToMany
    private List<Adresse> adresse;
    @ManyToOne
    @JoinColumn(name = "categorie")
    private Categorie categorie;
    @OneToMany
    private List<Serviceent> serviceents;
    @OneToMany
    private Set<Contact> contacts = new HashSet<>();
    @Column(unique = true)
    private String principalimage;
    @OneToMany(mappedBy = "centreInteret")
    private Set<Image> images = new HashSet<>();

    @PrePersist
    protected void onCreate() {this.created_at = new Date();}
    @PreUpdate
    protected void onUpdate() {this.updated_at = new Date();}

    public void addAdresse(Adresse adresse) {
        if(this.adresse == null) {
            this.adresse = new ArrayList<>();
        }
        this.adresse.add(adresse);
//        adresse.setCentreInteret(this);
    }
    public void addService(Serviceent serviceent) {
        if(this.serviceents == null) {
            this.serviceents = new ArrayList<>();
        }
        this.serviceents.add(serviceent);
//        serviceent.setCentreInteret(this);
    }
    public void addContact(Contact contact) {
        if(this.contacts == null) {
            this.contacts = new HashSet<>();
        }
        this.contacts.add(contact);
//        contact.setCentreInteret(this);
    }
    public void addImage(Image image) {
        if(this.images == null) {
            this.images = new HashSet<>();
        }
        this.images.add(image);
//        image.setCentreInteret(this);
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public String getPrincipalimage() {
        return principalimage;
    }

    public void setPrincipalimage(String principalimage) {
        this.principalimage = principalimage;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Adresse> getAdresse() {
        return adresse;
    }

    public void setAdresse(List<Adresse> adresse) {
        this.adresse = adresse;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
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
