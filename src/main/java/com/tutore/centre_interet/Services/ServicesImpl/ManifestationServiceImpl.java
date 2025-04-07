package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.DTO.ManifestationDTO;
import com.tutore.centre_interet.Entities.Manifestation;
import com.tutore.centre_interet.Entities.Serviceent;
import com.tutore.centre_interet.Repository.ManifestationRepository;
import com.tutore.centre_interet.Repository.ServiceRepo;
import com.tutore.centre_interet.Services.Manifestationservice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManifestationServiceImpl implements Manifestationservice {
    private ManifestationRepository manifestationRepository;
    private ServiceRepo serviceRepo;

    public ManifestationServiceImpl(ManifestationRepository manifestationRepository, ServiceRepo serviceRepo) {
        this.manifestationRepository = manifestationRepository;
        this.serviceRepo = serviceRepo;
    }

    public static ManifestationDTO toDTO(Manifestation manifestation) {
        if (manifestation == null) {
            return null;
        }
        ManifestationDTO dto = new ManifestationDTO();
        dto.setId(manifestation.getId());
        dto.setLibelle(manifestation.getLibelle());
        dto.setIduser(manifestation.getIduser());
//        dto.setCentreInterets(categorie.getCentreInterets());
        return dto;
    }

    @Override
    public ManifestationDTO save(Manifestation manifestation) {
        return toDTO(manifestationRepository.save(manifestation));
    }

    @Override
    public ManifestationDTO Delete(Long id) {
        return toDTO(manifestationRepository.deleteManifestationById(id));
    }

    @Override
    public List<ManifestationDTO> getall() {
        List<Manifestation> manifestations = manifestationRepository.findAll();
        List<ManifestationDTO> manifestationDTOS = manifestations.stream()
                .map(ManifestationServiceImpl::toDTO)
                .collect(Collectors.toList());
        return manifestationDTOS;
    }

    @Override
    public List<ManifestationDTO> getbyservice(Long idservice) {
        Serviceent serviceent = serviceRepo.findServiceById(idservice);
        List<Manifestation> manifestations = manifestationRepository.findManifestationByServiceent(serviceent);
        List<ManifestationDTO> manifestationDTOS = manifestations.stream()
                .map(ManifestationServiceImpl::toDTO)
                .collect(Collectors.toList());
        return manifestationDTOS;
    }

    @Override
    public List<ManifestationDTO> getallbyiduser(Long iduser) {
        List<Manifestation> manifestations = manifestationRepository.findAllByIduser(iduser);
        List<ManifestationDTO> manifestationDTOS = manifestations.stream()
                .map(ManifestationServiceImpl::toDTO)
                .collect(Collectors.toList());
        return manifestationDTOS;
    }
}
