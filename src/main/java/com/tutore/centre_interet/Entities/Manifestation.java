package com.tutore.centre_interet.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Manifestation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long iduser;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "servicemanifestation")
    @JsonBackReference
    private Serviceent serviceent;

    public Manifestation(Serviceent serviceent, String libelle, Long iduser, Long id) {
        this.serviceent = serviceent;
        this.libelle = libelle;
        this.iduser = iduser;
        this.id = id;
    }

    public Manifestation() {
    }

    public Serviceent getServiceent() {
        return serviceent;
    }

    public void setServiceent(Serviceent serviceent) {
        this.serviceent = serviceent;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
