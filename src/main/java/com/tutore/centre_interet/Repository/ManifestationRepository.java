package com.tutore.centre_interet.Repository;

import com.tutore.centre_interet.Entities.Manifestation;
import com.tutore.centre_interet.Entities.Serviceent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManifestationRepository extends JpaRepository<Manifestation, Long> {
    public List<Manifestation> findManifestationByServiceent(Serviceent serviceent);
    public Manifestation findManifestationById(Long id);
    public Manifestation deleteManifestationById(Long id);
    public List<Manifestation> findAllByIduser(Long iduser);
}
