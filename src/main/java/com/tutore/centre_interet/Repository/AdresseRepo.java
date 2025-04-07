package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Adresse;
import com.tutore.centre_interet.Entities.Coordonnes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepo extends JpaRepository<Adresse, Long> {
    public Adresse findAdresseById(Long id);
    public Adresse findAdresseByCoordonnes(Coordonnes coordonnes);
}
