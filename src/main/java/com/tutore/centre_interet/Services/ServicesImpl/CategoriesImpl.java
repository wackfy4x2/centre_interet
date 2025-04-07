package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.DTO.CategorieDTO;
import com.tutore.centre_interet.Entities.Categorie;
import com.tutore.centre_interet.Repository.CategorieRepo;
import com.tutore.centre_interet.Services.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesImpl implements CategoriesService {
    private CategorieRepo categorieRepo;

    public CategoriesImpl(CategorieRepo categorieRepo) {
        this.categorieRepo = categorieRepo;
    }


    public static CategorieDTO toDTO(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setNom(categorie.getNom());
//        dto.setCentreInterets(categorie.getCentreInterets());
        return dto;
    }

    @Override
    public CategorieDTO save(Categorie categorie) {
        categorie.setStatus(true);
        return toDTO(categorieRepo.save(categorie));
    }

    @Override
    public CategorieDTO Delete(Long id) {
        Categorie categorie = categorieRepo.findCategorieById(id);
        categorie.setStatus(false);
        return toDTO(categorieRepo.save(categorie));
    }

    @Override
    public List<Categorie> getall() {
        List<Categorie> categories = categorieRepo.findAllByStatus(true);
//        List<CategorieDTO> categorieDTOS = categories.stream()
//                .map(CategoriesImpl::toDTO)
//                .collect(Collectors.toList());
        return categories;
    }
}
