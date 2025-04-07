package com.tutore.centre_interet.DTO;

import com.tutore.centre_interet.Entities.CentreInteret;
import com.tutore.centre_interet.Entities.Coordonnes;
import lombok.Getter;
import lombok.Setter;


public class AdresseDTO {
    private Long id;
    private Coordonnes coordonnes;
    private String quartier;
    private String ville;
    private CentreInteret centreInteret;

    public CentreInteret getCentreInteret() {
        return centreInteret;
    }

    public void setCentreInteret(CentreInteret centreInteret) {
        this.centreInteret = centreInteret;
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
