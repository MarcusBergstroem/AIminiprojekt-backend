package com.example.miniaiprojekt.controller;

import com.example.miniaiprojekt.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/gemini")
@CrossOrigin("http://localhost:63342/")
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/bookhelper")
    public String bookkeeper() {
        return "bookhelper";
    }

    @GetMapping("/generate")
    @ResponseBody
    // Requestparam er den inputtekst som brugeren har skrevet i API-kaldet
    public ResponseEntity<String> generate(@RequestParam String prompt) {
        // Block her betyder at man venter på svaret fra API-kaldet
        String result = geminiService.generateText(prompt).block();
        return ResponseEntity.ok(result);
    }
}
