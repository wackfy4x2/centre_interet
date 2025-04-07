package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepo extends JpaRepository<Categorie, Long> {
    public Categorie findCategorieById(Long id);
    public List<Categorie> findAllByStatus(Boolean status);
}
