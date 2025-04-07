package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Coordonnes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoordonneRepo extends JpaRepository<Coordonnes, Long> {
    public Coordonnes findCoordonnesById(Long id);
}
