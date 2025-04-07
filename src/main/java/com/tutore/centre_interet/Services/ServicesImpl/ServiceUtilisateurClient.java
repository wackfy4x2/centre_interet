package com.tutore.centre_interet.Services.ServicesImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class ServiceUtilisateurClient {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ServiceUtilisateurClient.class);
    private final WebClient webClient;
    private final String jwtSecret = "votreSecret";

    public ServiceUtilisateurClient(@LoadBalanced WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://UTILISATEUR") // Nom du service dans Eureka
                .filter(logRequest())        // Logging des requêtes
                .build();
    }

    // Filtre pour logger les requêtes
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    // Méthode pour appeler Service B de manière réactive
    public Mono<Map<String, Object>> checkTokenExpiration(String token) {
        return webClient
                .post()
                .uri("/auth/isexpired")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .timeout(Duration.ofSeconds(5))
                .onErrorResume(error -> {
                    log.error("Error checking token expiration", error);
                    return Mono.just(Map.of(
                            "error", true,
                            "message", "Service unavailable",
                            "status", 500
                    ));
                });
    }

    // Gestion des erreurs
    private Mono<String> handleError(Throwable error) {
        log.error("Error calling Service B", error);
        if (error instanceof TimeoutException) {
            return Mono.just("Service B timeout - fallback response");
        }
        return Mono.just("Service B error - fallback response");
    }

    public Claims extractAllClaims(String header) {
        String token = header.substring(7);
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Le token a expiré", e);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Token JWT invalide", e);
        }
    }
}
