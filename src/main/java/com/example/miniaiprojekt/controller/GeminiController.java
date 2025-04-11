package com.example.miniaiprojekt.controller;

import com.example.miniaiprojekt.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/")
    public String index() {
        return "Hej med dig";
    }

    @GetMapping("/bookhelper")
    public String bookkeeper() {
        return "/bookhelper.html";
    }

    @GetMapping("/generate")
    // Requestparam er den inputtekst som brugeren har skrevet i API-kaldet
    public ResponseEntity<String> generate(@RequestParam String prompt) {
        // Block her betyder at man venter på svaret fra API-kaldet
        String result = geminiService.generateText(prompt).block();
        return ResponseEntity.ok(result);
    }
}
