package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.DTO.CentreDTO;
import com.tutore.centre_interet.Entities.Adresse;
import com.tutore.centre_interet.Entities.CentreInteret;
import com.tutore.centre_interet.Entities.Contact;
import com.tutore.centre_interet.Entities.Serviceent;
import com.tutore.centre_interet.Services.ServicesImpl.CentreInteretServiceImpl;
import com.tutore.centre_interet.Services.ServicesImpl.ServiceUtilisateurClient;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("centres")
public class CentreInteretController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CentreInteretController.class);
    private CentreInteretServiceImpl centreInteretService;
    private ServiceUtilisateurClient serviceUtilisateurClient;

    public CentreInteretController(CentreInteretServiceImpl centreInteretService, ServiceUtilisateurClient serviceUtilisateurClient) {
        this.centreInteretService = centreInteretService;
        this.serviceUtilisateurClient = serviceUtilisateurClient;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@RequestHeader("Authorization") String authHeader, @RequestBody CentreInteret centreInteret) {
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        CentreDTO centreDTO = new CentreDTO();
        Map<String, Object> rep = new HashMap<>();
        if ((role.compareTo("Administrateur") == 0) || (role.compareTo("Collecteur") == 0)) {
            centreInteret.setIduser(Long.valueOf(String.valueOf(claims.get("id_utilisateur"))));
            centreDTO = centreInteretService.save(centreInteret);
        } else {
            rep.put("message", "vous n'avez pas les autorisation necessaire");
            return rep;
        }
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de l'enregistrement");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", "enregistrer avec succes");
        return rep;
    }

    @GetMapping("/list/{pageNo}/{pageSize}")
    public Map<String, Object> read(@RequestHeader("Authorization") String authHeader, @PathVariable Integer pageNo,
                                    @PathVariable Integer pageSize) {
        log.info(authHeader);
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        List<CentreDTO> centreDTOS = new ArrayList<>();
        if ((role.compareTo("Administrateur") == 0) || (role.compareTo("Moderateur") == 0)) {
            centreDTOS = centreInteretService.findall(pageNo, pageSize);
        }
        if ((role.compareTo("Usager") == 0) || (authHeader.compareTo("null") == 0)) {
            centreDTOS = centreInteretService.findbystatus(true);
        }
        if (role.compareTo("Collecteur") == 0) {
            Long id = Long.valueOf(String.valueOf(claims.get("id_utilisateur")));
            centreDTOS = centreInteretService.findbyiduser(id);
        }
        Map<String, Object> rep = new HashMap<>();
        if (centreDTOS == null) {
            rep.put("success", false);
            rep.put("message", "liste vide");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTOS);
        return rep;
    }

    @GetMapping("/listforusager/{pageNo}/{pageSize}")
    public Map<String, Object> readusager(@PathVariable Integer pageNo,
                                          @PathVariable Integer pageSize) {
        List<CentreDTO> centreDTOS = new ArrayList<>();
        centreDTOS = centreInteretService.findall(pageNo, pageSize);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTOS == null) {
            rep.put("success", false);
            rep.put("message", "liste vide");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTOS);
        return rep;
    }


    @GetMapping("/list")
    public Map<String, Object> readp(@RequestHeader("Authorization") String authHeader) {
        log.info(authHeader);
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        List<CentreDTO> centreDTOS = new ArrayList<>();
        if ((role.compareTo("Administrateur") == 0) || (role.compareTo("Moderateur") == 0)) {
            centreDTOS = centreInteretService.findallp();
        }
        if ((role.compareTo("Usager") == 0) || (authHeader.compareTo("null") == 0)) {
            centreDTOS = centreInteretService.findbystatus(true);
        }
        if (role.compareTo("Collecteur") == 0) {
            Long id = Long.valueOf(String.valueOf(claims.get("id_utilisateur")));
            centreDTOS = centreInteretService.findbyiduser(id);
        }
        Map<String, Object> rep = new HashMap<>();
        if (centreDTOS == null) {
            rep.put("success", false);
            rep.put("message", "liste vide");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTOS);
        return rep;
    }

    @GetMapping("/listforusager")
    public Map<String, Object> readforusager() {
        List<CentreDTO> centreDTOS = new ArrayList<>();
        centreDTOS = centreInteretService.findbystatus(true);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTOS == null) {
            rep.put("success", false);
            rep.put("message", "liste vide");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTOS);
        return rep;
    }

    @PutMapping("/")
    public Map<String, Object> update(@RequestHeader("Authorization") String authHeader, @RequestBody CentreInteret centreInteret) {
        Map<String, Object> rep = new HashMap<>();
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        CentreDTO centreDTO = new CentreDTO();
        if ((role.compareTo("Collecteur") == 0) || (role.compareTo("Administrateur") == 0)) {
            if (role.compareTo("Collecteur") == 0) {
                Long id = (Long) claims.get("id_utilisateur");
                if (id == centreInteret.getIduser()) {
                    centreDTO = centreInteretService.update(centreInteret);
                }
            } else {
                centreDTO = centreInteretService.update(centreInteret);
            }
            log.info(centreDTO.getDescription());
        } else {
            rep.put("message", "vous n'etes pas autoriser");
            return rep;
        }
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de la modification");
//            return rep;
        } else {
            rep.put("success", true);
        }
//        rep.put("message", centreDTO);
        return rep;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> delete(@RequestBody Long id, @RequestHeader("Authorization") String authHeader) {
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        CentreDTO centreDTO = new CentreDTO();
        if (role.compareTo("Collecteur") == 0) {
            CentreDTO centreDTO1 = centreInteretService.findbyid(id);
            Long iduser = Long.valueOf(String.valueOf(claims.get("id_utilisateur")));
            if (centreDTO1.getIduser() == iduser) {
                centreDTO = centreInteretService.delete(id);
            }
        }
        Map<String, Object> rep = new HashMap<>();
        if (!centreDTO.isStatut()) {
            rep.put("success", false);
            rep.put("message", "Echec de la suppression");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", "Supprime avec succes");
        return rep;
    }

    @PutMapping("verifier")
    public Map<String, Object> verifier(@RequestBody Long id) {
        CentreDTO centreDTO = centreInteretService.verifier(id);
        Map<String, Object> rep = new HashMap<>();
        if (!centreDTO.isStatut()) {
            rep.put("success", false);
            rep.put("message", "verification a echouer");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", "verifier avec success");
        return rep;
    }

    @GetMapping("/{id}")
    public Map<String, Object> findbyid(@PathVariable Long id) {
        CentreDTO centreDTO = centreInteretService.findbyid(id);
        Map<String, Object> rep = new HashMap<>();
        if (!centreDTO.isStatut()) {
            rep.put("success", false);
            rep.put("message", "item doesn t exist");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTO);
        return rep;
    }

    @GetMapping("/ville/{ville}")
    public Map<String, Object> findbyville(@PathVariable String ville) {
        List<CentreDTO> centreDTO = centreInteretService.findbyVille(ville);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de la recherche");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTO);
        return rep;
    }

    @GetMapping("/service/{service}")
    public Map<String, Object> findbyservice(@PathVariable String service) {
        List<CentreDTO> centreDTO = centreInteretService.findbyservice(service);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de la suppression");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTO);
        return rep;
    }

    @GetMapping("/localisation/{lattitude}/{longitude}")
    public Map<String, Object> findbylocation(@PathVariable double lattitude, @PathVariable double longitude) {
        CentreDTO centreDTO = centreInteretService.findbylocation(lattitude, longitude);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "element inexistant");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTO);
        return rep;
    }

    @PostMapping("/newservice/{idcentre}")
    public Map<String, Object> addservice(@RequestBody Serviceent serviceent, @PathVariable Long idcentre) {
        CentreDTO centreDTO = centreInteretService.ajouter_Service(serviceent, idcentre);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de l'ajout");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTO);
        return rep;
    }

    @PostMapping("/newcontact/{idcentre}")
    public Map<String, Object> addcontact(@RequestBody Contact contact, @PathVariable Long idcentre) {
        CentreDTO centreDTO = centreInteretService.ajouter_Contact(contact, idcentre);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de l'ajout");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", "contact Ajouter avec Success");
        return rep;
    }

    @PostMapping("/newadresse/{idcentre}")
    public Map<String, Object> addadresse(@RequestBody Adresse adresse, @PathVariable Long idcentre) {
        CentreDTO centreDTO = centreInteretService.ajouter_adresse(adresse, idcentre);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "il n y a pas");
            return rep;
        }
        rep.put("success", true);
//        rep.put("message", centreDTO);
        return rep;
    }

    @PostMapping("/newadressM/{idcentre}")
    public Map<String, Object> addadresseMigration(@RequestBody Adresse adresse, @PathVariable String idcentre) {
        CentreDTO centreDTO = centreInteretService.ajouter_adresseMigration(adresse, idcentre);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "il n y a pas");
            return rep;
        }
        rep.put("success", true);
//        rep.put("message", centreDTO);
        return rep;
    }

    @GetMapping("near/{lattitude}/{longitude}")
    public Map<String, Object> getnearest(@PathVariable Double lattitude, @PathVariable Double longitude) {
        List<CentreDTO> centreDTOS = new ArrayList<>();
        centreDTOS = centreInteretService.getNearest(lattitude, longitude);
        Map<String, Object> rep = new HashMap<>();
        if (centreDTOS == null) {
            rep.put("success", false);
            rep.put("message", "liste vide");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTOS);
        return rep;
    }
}
