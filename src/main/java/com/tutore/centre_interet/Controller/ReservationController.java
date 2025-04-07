package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.DTO.ReservationDTO;
import com.tutore.centre_interet.Entities.Reservation;
import com.tutore.centre_interet.Services.ServicesImpl.ReservationServiceImpl;
import com.tutore.centre_interet.Services.ServicesImpl.ServiceUtilisateurClient;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReservationController {
    private ReservationServiceImpl reservationService;
    private ServiceUtilisateurClient serviceUtilisateurClient;

    public ReservationController(ReservationServiceImpl reservationService, ServiceUtilisateurClient serviceUtilisateurClient) {
        this.reservationService = reservationService;
        this.serviceUtilisateurClient = serviceUtilisateurClient;
    }

    @PostMapping("save")
    public Map<String, Object> save(@RequestBody Reservation reservation, @RequestHeader("Authorization") String authHeader) {
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        Map<String, Object> response = new HashMap<>();
        if (role.compareTo("Usager") == 0) {
            Long id = Long.valueOf(String.valueOf(claims.get("id_utilisateur")));
            reservation.setIduser(id);
            ReservationDTO reservationDTO = reservationService.save(reservation);
            response.put("message", "enregistrer avec success");
        }
        return response;
    }

    @GetMapping("/all")
    public Map<String, Object> getreservation(@RequestHeader("Authorization") String authHeader) {
        Claims claims = serviceUtilisateurClient.extractAllClaims(authHeader);
        List<Map<String, Object>> roles = (List<Map<String, Object>>) claims.get("roles");
        String role = roles.get(0).get("libelle").toString();
        List<ReservationDTO> centreDTO = new ArrayList<>();
        if (role.compareTo("Usager") == 0) {
            centreDTO = reservationService.findall(Long.valueOf(String.valueOf(claims.get("id_utilisateur"))));
        }
        Map<String, Object> rep = new HashMap<>();
        if (centreDTO == null) {
            rep.put("success", false);
            rep.put("message", "Echec de la recuperation");
            return rep;
        }
        rep.put("success", true);
        rep.put("message", centreDTO);
        return rep;
    }
}
