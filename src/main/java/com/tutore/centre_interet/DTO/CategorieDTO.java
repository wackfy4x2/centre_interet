package com.tutore.centre_interet.DTO;

import com.tutore.centre_interet.Entities.CentreInteret;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class CategorieDTO {

    private Long id;
    private String nom;
//    private Set<CentreInteret> centreInterets;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
