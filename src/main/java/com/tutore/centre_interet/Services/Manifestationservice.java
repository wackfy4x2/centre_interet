package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.CategorieDTO;
import com.tutore.centre_interet.DTO.ManifestationDTO;
import com.tutore.centre_interet.Entities.Categorie;
import com.tutore.centre_interet.Entities.Manifestation;

import java.util.List;

public interface Manifestationservice {
    public ManifestationDTO save(Manifestation manifestation);
    public ManifestationDTO Delete(Long id);
    public List<ManifestationDTO> getall();
    public List<ManifestationDTO> getbyservice(Long idservice);
    public List<ManifestationDTO> getallbyiduser(Long iduser);
}
