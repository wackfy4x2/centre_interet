package com.tutore.centre_interet.Services.ServicesImpl;

import com.tutore.centre_interet.DTO.CentreDTO;
import com.tutore.centre_interet.Entities.*;
import com.tutore.centre_interet.Repository.*;
import com.tutore.centre_interet.Services.CentreInteretService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

@Service
@Transactional
public class CentreInteretServiceImpl implements CentreInteretService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CentreInteretServiceImpl.class);
    private final ServiceRepo serviceRepo;
    private final ContactRepo contactRepo;
    private final AdresseRepo adresseRepo;
    private ImageRepository imageRepository;
    private CentreInteretRepo centreInteretRepo;
    private CoordonneRepo coordonneRepo;

    public CentreInteretServiceImpl(ServiceRepo serviceRepo, ContactRepo contactRepo, AdresseRepo adresseRepo, ImageRepository imageRepository, CentreInteretRepo centreInteretRepo, CoordonneRepo coordonneRepo) {
        this.serviceRepo = serviceRepo;
        this.contactRepo = contactRepo;
        this.adresseRepo = adresseRepo;
        this.imageRepository = imageRepository;
        this.centreInteretRepo = centreInteretRepo;
        this.coordonneRepo = coordonneRepo;
    }

    public static CentreDTO toDTO(CentreInteret centreInteret) {
        if (centreInteret == null) {
            return null;
        }
        CentreDTO centreDTO = new CentreDTO();
        centreDTO.setId(centreInteret.getId());
        centreDTO.setDescription(centreInteret.getDescription());
        centreDTO.setNom(centreInteret.getNom());
        centreDTO.setServiceents(centreInteret.getServiceents());
        centreDTO.setStatut(centreInteret.isStatut());
        centreDTO.setContacts(centreInteret.getContacts());
        centreDTO.setAdresse(centreInteret.getAdresse());
        centreDTO.setCategorie(centreInteret.getCategorie());
        centreDTO.setImages(centreInteret.getImages());
        centreDTO.setPrincipaleimage(centreInteret.getPrincipalimage());
        centreDTO.setIduser(centreInteret.getIduser());
        return centreDTO;
    }

    @Override
    public CentreDTO findbyid(Long id) {
        return toDTO(centreInteretRepo.findCentreInteretById(id));
    }

    @Override
    public List<CentreDTO> findall(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<CentreInteret> centreInterets = centreInteretRepo.findAll(paging);
        List<CentreDTO> centreDTOS = centreInterets.stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        return centreDTOS;
    }

    @Override
    public List<CentreDTO> findallp() {
        List<CentreInteret> centreInterets = centreInteretRepo.findAll();
        List<CentreDTO> centreDTOS = centreInterets.stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        return centreDTOS;
    }

    @Override
    public CentreDTO save(CentreInteret centreInteret) {
        centreInteret.setStatut(true);
        CentreDTO centreDTO = toDTO(centreInteretRepo.save(centreInteret));
//        image1.setCentreInteret(centreInteretRepo.findCentreInteretByNom(centreInteret.getNom()));
//        imageRepository.save(image1);
        return centreDTO;
    }

    @Override
    public List<CentreDTO> findbystatus(Boolean status) {
        List<CentreInteret> centreInterets = centreInteretRepo.findCentreInteretByStatut(status);
        List<CentreDTO> centreDTOS = centreInterets.stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        return centreDTOS;
    }

    @Override
    public CentreDTO update(CentreInteret centreInteret) {
        CentreInteret centreInteret1 = centreInteretRepo.findCentreInteretById(centreInteret.getId());
        log.info(centreInteret1.getDescription());
        if (centreInteret1 != null) {
            centreInteret.setId(centreInteret1.getId());
            return toDTO(centreInteretRepo.save(centreInteret));
        }
        return null;
    }

    @Override
    public CentreDTO verifier(Long id) {
        CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(id);
        centreInteret.setStatut(true);
        centreInteret.setId(centreInteret.getId());
        return update(centreInteret);
    }

    @Override
    public CentreDTO delete(Long id) {
        CentreInteret centreInteret = centreInteretRepo.deleteCentreInteretById(id);
        return toDTO(centreInteret);
    }

    @Override
    public CentreDTO findbyname(String name) {
        return toDTO(centreInteretRepo.findCentreInteretByNom(name));
    }

    @Override
    public List<CentreDTO> findbyVille(String ville) {
        List<CentreDTO> centreDTOS = centreInteretRepo.findAll().stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        for (CentreDTO centreDTO : centreDTOS) {
            for (Adresse adresse : centreDTO.getAdresse())
                if (!adresse.getVille().equals(ville))
                    centreDTOS.remove(centreDTO);
        }
        return centreDTOS;
    }

    @Override
    public List<CentreDTO> findbyservice(String nomService) {
        List<CentreDTO> centreDTOS = centreInteretRepo.findAll().stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        for (CentreDTO centreDTO : centreDTOS) {
            for (Serviceent serviceent : centreDTO.getServiceents())
                if (!serviceent.getNom().equals(nomService))
                    centreDTOS.remove(centreDTO);
        }
        return centreDTOS;
    }

    @Override
    public CentreDTO findbylocation(double latitude, double longitude) {
        List<CentreDTO> centreDTOS = centreInteretRepo.findAll().stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        for (CentreDTO centreDTO : centreDTOS) {
            for (Adresse adresse : centreDTO.getAdresse()) {
                if (adresse.getCoordonnes().getLattitude() == latitude && adresse.getCoordonnes().getLongitude() == longitude)
                    return centreDTO;
            }
        }
        return null;
    }

    @Override
    public CentreDTO ajouter_Service(Serviceent serviceent, Long idcentre) {
        serviceent.setStatus(true);
        serviceRepo.save(serviceent);
        CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(idcentre);
        centreInteret.addService(serviceRepo.findServiceentByNom(serviceent.getNom()));
        return toDTO(centreInteretRepo.save(centreInteret));
    }

    @Override
    public CentreDTO supprimer_Service(Long idcentre, Long idservice) {
        CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(idcentre);
        Serviceent serviceent = serviceRepo.findServiceById(idservice);
        centreInteret.getServiceents().remove(serviceent);
        return toDTO(centreInteretRepo.save(centreInteret));
    }

    @Override
    public CentreDTO ajouter_Contact(Contact contact, Long idcentre) {
        contact.setStatus(true);
        Contact contact1 = contactRepo.save(contact);
        CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(idcentre);
        centreInteret.addContact(contact1);
        return toDTO(centreInteretRepo.save(centreInteret));
    }

    @Override
    public CentreDTO supprimer_Contact(Long idcentre, Long idcontact) {
        CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(idcentre);
        Contact contact = contactRepo.findContactById(idcontact);
        centreInteret.getContacts().remove(contact);
        return toDTO(centreInteretRepo.save(centreInteret));
    }

    @Override
    public CentreDTO ajouter_adresse(Adresse adresse, Long idcentre) {
        try {
            // Calculer l'ID unique avec une méthode plus robuste
            log.info(String.valueOf(adresse.getCoordonnes().getLattitude()));
//            long uniqueId = generateUniqueCoordinateId(
//                    adresse.getCoordonnes().getLattitude(),
//                    adresse.getCoordonnes().getLongitude()
//            );

            // Vérifier si les coordonnées existent déjà
//            Optional<Coordonnes> existingCoord = coordonneRepo.findById(uniqueId);
//            while (existingCoord.isPresent()) {
//                // Utiliser les coordonnées existantes
//                uniqueId = generateUniqueCoordinateId(
//                        adresse.getCoordonnes().getLattitude()+1,
//                        adresse.getCoordonnes().getLongitude()+1
//                );
//            }
            // Créer nouvelles coordonnées
            Coordonnes coordonnes = new Coordonnes();
            coordonnes.setLattitude(adresse.getCoordonnes().getLattitude());
            coordonnes.setLongitude(adresse.getCoordonnes().getLongitude());
            Coordonnes coordonnes1 = coordonneRepo.save(coordonnes);
            coordonnes.setId(coordonnes1.getId());
            adresse.setCoordonnes(coordonnes);
//            }

            // Sauvegarder l'adresse
            adresseRepo.save(adresse);

            // Mettre à jour le centre d'intérêt
            CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(idcentre);
            if (centreInteret == null) {
                throw new EntityNotFoundException("Centre d'intérêt non trouvé avec l'ID: " + idcentre);
            }

            centreInteret.addAdresse(adresse);
            return toDTO(centreInteretRepo.save(centreInteret));

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'adresse: " + e.getMessage(), e);
        }
    }

    public CentreDTO ajouter_adresseMigration(Adresse adresse, String nomcentre) {
        try {
            // Calculer l'ID unique avec une méthode plus robuste
            log.info(String.valueOf(adresse.getCoordonnes().getLattitude()));

//            long uniqueId = generateUniqueCoordinateId(
//                    adresse.getCoordonnes().getLattitude(),
//                    adresse.getCoordonnes().getLongitude()
//            );
//            log.info(String.valueOf(uniqueId));

            // Vérifier si les coordonnées existent déjà
//            Optional<Coordonnes> existingCoord = coordonneRepo.findById(uniqueId);
//            while (existingCoord.isPresent()) {
//                // Utiliser les coordonnées existantes
//                uniqueId = generateUniqueCoordinateId(
//                        adresse.getCoordonnes().getLattitude()+1,
//                        adresse.getCoordonnes().getLongitude()+1
//                );
//            }
            // Créer nouvelles coordonnées
            Coordonnes coordonnes = new Coordonnes();
            coordonnes.setLattitude(adresse.getCoordonnes().getLattitude());
            coordonnes.setLongitude(adresse.getCoordonnes().getLongitude());
            Coordonnes coordonnes1 = coordonneRepo.save(coordonnes);
            coordonnes.setId(coordonnes1.getId());
            adresse.setCoordonnes(coordonnes);
//            }

            // Sauvegarder l'adresse
            adresseRepo.save(adresse);

            // Mettre à jour le centre d'intérêt
            CentreInteret centreInteret = centreInteretRepo.findCentreInteretByNom(nomcentre);
            if (centreInteret == null) {
                throw new EntityNotFoundException("Centre d'intérêt non trouvé avec l'ID: " + centreInteret);
            }

            centreInteret.addAdresse(adresse);
            return toDTO(centreInteretRepo.save(centreInteret));

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'ajout de l'adresse: " + e.getMessage(), e);
        }
    }

    private long generateUniqueCoordinateId(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new IllegalArgumentException("Les coordonnées ne peuvent pas être nulles");
        }

        // Convertir en format sans décimale pour éviter les problèmes de précision
        long latInt = Math.round(latitude * 1000000);
        long lonInt = Math.round(longitude * 1000000);

        // Utiliser un algorithme de hachage pour générer un ID unique
        // Cette méthode assure une meilleure distribution des IDs
        String combined = String.format("%d_%d", latInt, lonInt);
        return Math.abs(combined.hashCode());
    }

    @Override
    public CentreDTO supprimer_adresse(Long idcentre, Long idadresse) {
        CentreInteret centreInteret = centreInteretRepo.findCentreInteretById(idcentre);
        Adresse adresse = adresseRepo.findAdresseById(idadresse);
        centreInteret.getAdresse().remove(adresse);
        return toDTO(centreInteretRepo.save(centreInteret));
    }

    @Override
    public List<CentreDTO> findbyiduser(Long iduser) {
        List<CentreInteret> centreInterets = centreInteretRepo.findCentreInteretByIduser(iduser);
        List<CentreDTO> centreDTOS = centreInterets.stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
        return centreDTOS;
    }

    @Override
    public List<CentreDTO> getNearest(Double latitude, Double longitude) {
        List<CentreInteret> centreInterets = centreInteretRepo.findAll();
        List<CentreInteret> nearestCentres = new ArrayList<>();

        for (CentreInteret centre : centreInterets) {
            log.info("test");
            for (Adresse adresse : centre.getAdresse()) {
                double distance = distance(adresse.getCoordonnes().getLattitude(),
                        adresse.getCoordonnes().getLongitude(),
                        latitude, longitude);
                log.info(String.valueOf(distance));
                if (distance <= 2.0) {  // Assurez-vous que cette unité est en kilomètres ou ajustez selon votre besoin
                    nearestCentres.add(centre);
                    break;  // Ajoutez le centre une seule fois même s'il a plusieurs adresses proches
                }
            }
        }

        return nearestCentres.stream()
                .map(CentreInteretServiceImpl::toDTO)
                .collect(Collectors.toList());
    }

    public static double distance(Double lat1, Double lon1, Double lat2, Double lon2) {
        // Rayon moyen de la Terre en kilomètres
        final double R = 6371.0;

        // Conversion des degrés en radians
        double latDistance = toRadians(lat2 - lat1);
        double lonDistance = toRadians(lon2 - lon1);

        double a = sin(latDistance / 2) * sin(latDistance / 2)
                + cos(toRadians(lat1)) * cos(toRadians(lat2))
                * sin(lonDistance / 2) * sin(lonDistance / 2);

        double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return R * c; // Distance en kilomètres
    }
}
