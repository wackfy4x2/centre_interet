package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.DTO.CentreDTO;
import com.tutore.centre_interet.DTO.ManifestationDTO;
import com.tutore.centre_interet.Entities.Manifestation;
import com.tutore.centre_interet.Services.ServicesImpl.ManifestationServiceImpl;
import com.tutore.centre_interet.Services.ServicesImpl.ServiceUtilisateurClient;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("manifestation")
public class ManifestationController {
    private ManifestationServiceImpl manifestationService;
    private ServiceUtilisateurClient serviceUtilisateurClient;

    public ManifestationController(ManifestationServiceImpl manifestationService, ServiceUtilisateurClient serviceUtilisateurClient) {
        this.manifestationService = manifestationService;
        this.serviceUtilisateurClient = serviceUtilisateurClient;
    }

    @PostMapping("create")
    public Map<String, Object> save(@RequestBody Manifestation manifestation, @RequestHeader("Authorization") String authHeader) {
        Map<String, Object> response = new HashMap<>();
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<CentreDTO> centreDTOS = new ArrayList<>();
        List<ManifestationDTO> manifestationDTOS = manifestationService.getallbyiduser(Long.valueOf(String.valueOf(claims.get("id_utilisateur"))));
        if (manifestationDTOS.size() >= 1) {
            response.put("message", "vous avez deja liker");
            return response;
        }
        ManifestationDTO manifestationDTO = manifestationService.save(manifestation);
        if (manifestationDTO == null) {
            response.put("message", "echec de l'enregistrement");
        } else {
            response.put("message", "enregistrer avec success");
        }
        return response;
    }

    @DeleteMapping("delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        ManifestationDTO manifestationDTO = manifestationService.Delete(id);
        if (manifestationDTO == null) {
            response.put("message", "echec de la suppression");
        } else {
            response.put("message", "supprimer avec success");
        }
        return response;
    }

    @GetMapping("all")
    public Map<String, Object> getall() {
        Map<String, Object> response = new HashMap<>();
        List<ManifestationDTO> manifestationDTO = manifestationService.getall();
        if (manifestationDTO == null) {
            response.put("message", "echec de la selection des donnees");
        } else {
            response.put("message", "selectionner avec success");
            response.put("list", manifestationDTO);
        }
        return response;
    }

    @GetMapping("all/{iduser}")
    public Map<String, Object> getallbyiduser(@PathVariable Long iduser) {
        Map<String, Object> response = new HashMap<>();
        List<ManifestationDTO> manifestationDTO = manifestationService.getallbyiduser(iduser);
        if (manifestationDTO == null) {
            response.put("message", "echec de la selection des donnees");
        } else {
            response.put("message", "selectionner avec success");
            response.put("list", manifestationDTO);
        }
        return response;
    }

    @GetMapping("buservice/{idservice}")
    public Map<String, Object> getbyidservice(@PathVariable Long idservice) {
        Map<String, Object> response = new HashMap<>();
        List<ManifestationDTO> manifestationDTO = manifestationService.getbyservice(idservice);
        if (manifestationDTO == null) {
            response.put("message", "echec de la selection");
        } else {
            response.put("message", "selectionner avec success");
            response.put("list", manifestationDTO);
        }
        return response;
    }
}
