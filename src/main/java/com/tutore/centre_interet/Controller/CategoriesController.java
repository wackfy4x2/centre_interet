package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.DTO.CategorieDTO;
import com.tutore.centre_interet.Entities.Categorie;
import com.tutore.centre_interet.Services.ServicesImpl.CategoriesImpl;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("categories")
public class CategoriesController {
    private CategoriesImpl categories;

    public CategoriesController(CategoriesImpl categories) {
        this.categories = categories;
    }

    @PostMapping("")
    private Map<String, Object> save(@RequestBody Categorie categorie) {
        CategorieDTO categorieDTO = categories.save(categorie);
        Map<String, Object> response = new HashMap<>();
        if (categorieDTO == null) {
            response.put("message", "echec de l'enregistrement");
        } else {
            response.put("message", "success");
        }
        return response;
    }

    @PutMapping("del")
    private Map<String, Object> delete(@RequestBody Long id) {
        CategorieDTO categorieDTO = categories.Delete(id);
        Map<String, Object> response = new HashMap<>();
        if (categorieDTO == null) {
            response.put("message", "echec de la suppression");
        } else {
            response.put("message", "supprimer avec success");
        }
        return response;
    }

    @GetMapping("")
    private Map<String, Object> findall() {
        List<Categorie> categorieDTO = categories.getall();
        Map<String, Object> response = new HashMap<>();
        if (categorieDTO == null) {
            response.put("message", "echec de la suppression");
        } else {
            response.put("message", "supprimer avec success");
            response.put("categories", categorieDTO);
        }
        return response;
    }
}
