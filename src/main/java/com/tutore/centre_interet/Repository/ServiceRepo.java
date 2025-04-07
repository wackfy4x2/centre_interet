package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Serviceent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Serviceent, Long> {
    public Serviceent findServiceById(Long id);
    public Serviceent findServiceentByNom(String nom);
}