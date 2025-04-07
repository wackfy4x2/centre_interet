package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.CentreDTO;
import com.tutore.centre_interet.Entities.Adresse;
import com.tutore.centre_interet.Entities.CentreInteret;
import com.tutore.centre_interet.Entities.Contact;
import com.tutore.centre_interet.Entities.Serviceent;

import java.util.List;

public interface CentreInteretService {
    public List<CentreDTO> findallp();
    public List<CentreDTO> findall(Integer pageNo, Integer pageSize);
    public CentreDTO findbyid(Long id);
    public List<CentreDTO> findbyservice(String nomService);
    public CentreDTO findbylocation(double latitude, double longitude);
    public CentreDTO save(CentreInteret centreInteret);
    public CentreDTO update(CentreInteret centreInteret);
    public CentreDTO delete(Long id);
    public CentreDTO verifier(Long id);
    public List<CentreDTO> findbystatus(Boolean status);
    public CentreDTO findbyname(String name);
    public List<CentreDTO> findbyVille(String ville);

//    Fonctionnalite liee au service
    public CentreDTO ajouter_Service(Serviceent serviceent, Long id);
    public CentreDTO supprimer_Service(Long idcentre, Long idservice);

//    Fonctionnalite liee au contact
    public CentreDTO ajouter_Contact(Contact contact, Long id);
    public CentreDTO supprimer_Contact(Long idcentre, Long idcontact);

//    Fonctionnalite liee a l'adresse
    public CentreDTO ajouter_adresse(Adresse adresse, Long id);
    public CentreDTO supprimer_adresse(Long idcentre, Long idadresse);
    public List<CentreDTO> findbyiduser(Long iduser);

//    centre les plus proche
    public List<CentreDTO> getNearest(Double latitude, Double Longitude);
}