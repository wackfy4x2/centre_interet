package com.tutore.centre_interet.Controller;

import com.tutore.centre_interet.Services.ServicesImpl.ServiceUtilisateurClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Authentification {
    private ServiceUtilisateurClient serviceUtilisateurClient;

    public Authentification(ServiceUtilisateurClient serviceUtilisateurClient) {
        this.serviceUtilisateurClient = serviceUtilisateurClient;
    }

    @PostMapping("/expired")
    public Map<String, Object> expiration(@RequestBody String token) {
        return serviceUtilisateurClient.checkTokenExpiration(token).block();
    }
}
