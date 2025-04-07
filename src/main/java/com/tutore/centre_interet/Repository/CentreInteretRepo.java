package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.CentreInteret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentreInteretRepo extends JpaRepository<CentreInteret, Long > {
    public CentreInteret findCentreInteretById(Long id);
    public CentreInteret findCentreInteretByNom(String nom);
    public List<CentreInteret> findCentreInteretByIduser(Long iduser);
    public CentreInteret deleteCentreInteretById(Long id);
    public List<CentreInteret> findCentreInteretByStatut(Boolean status);
//    public List<CentreInteret> findCentreInteretByNomOrDescriptionContainingIgnoreCase(String searchi);
}
