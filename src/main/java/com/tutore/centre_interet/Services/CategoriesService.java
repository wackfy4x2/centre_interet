package com.tutore.centre_interet.Services;

import com.tutore.centre_interet.DTO.CategorieDTO;
import com.tutore.centre_interet.Entities.Categorie;

import java.util.List;

public interface CategoriesService {
    public CategorieDTO save(Categorie categorie);
    public CategorieDTO Delete(Long id);
    public List<Categorie> getall();
}
