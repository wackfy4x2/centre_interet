package com.tutore.centre_interet.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
public class Serviceent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(unique = true)
    private String nom;
    private String description;
    private double prix;
    private Boolean status;
    @OneToMany(mappedBy = "serviceent")
    private Set<Manifestation> manifestations;
    @OneToMany(mappedBy = "serviceent")
    private Set<Reservation> reservations;
    private String image;
    private Date created_at;
    private Date updated_at;
//    @ManyToOne
//    @JoinColumn(name = "servicesinteret")
//    private CentreInteret centreInteret;

    @PrePersist
    protected void onCreate() {this.created_at = new Date();}
    @PreUpdate
    protected void onUpdate() {this.updated_at = new Date();}

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Manifestation> getManifestations() {
        return manifestations;
    }

    public void setManifestations(Set<Manifestation> manifestations) {
        this.manifestations = manifestations;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
