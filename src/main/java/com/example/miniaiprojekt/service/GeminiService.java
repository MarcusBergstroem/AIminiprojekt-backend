package com.example.miniaiprojekt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class GeminiService {

    // Her defineres vores API-nøgle som vi har fået gratis fra Gemini
    @Value("AIzaSyAumJSG6IdP8U7m_sfHAxZ1PpVtP2_ONuc")
    private String apiKey;

    // Forklaring fra Chat-GPT: Spring Boot’s reaktive HTTP-klient, som bruges til at sende HTTP-forespørgsler
    // og modtage svar – ligesom Postman, men i kode.
    private final WebClient webClient;

    // Her injicerer vi et webclient objekt.
    // Det kræver at vi har WebClientConfig som definerer et Bean
    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateText(String prompt) {
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))
                )
        );

        // Selve HTTP-kaldet til Gemini: sender en post
        // Returnerer svaret som Mono<map> som vi så mapper til en String
        return webClient.post()
                .uri(endpoint)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    try {
                        List<Map> candidates = (List<Map>) response.get("candidates");
                        Map content = (Map) candidates.get(0).get("content");
                        List<Map> parts = (List<Map>) content.get("parts");
                        return (String) parts.get(0).get("text");
                    } catch (Exception e) {
                        return "Fejl ved parsing af svar: " + e.getMessage();
                    }
                });
    }
}

