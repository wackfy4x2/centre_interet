package com.tutore.centre_interet.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Coordonnes coordonnes;
    private String quartier;
    private String ville;
    private Date created_at;
    private Date updated_at;

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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public Coordonnes getCoordonnes() {
        return coordonnes;
    }

    public void setCoordonnes(Coordonnes coordonnes) {
        this.coordonnes = coordonnes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
