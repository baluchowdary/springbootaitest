package com.kollu.ai.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kollu.ai.model.PromptRequest;
import com.kollu.ai.service.OllamaService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class OllamaController {

    private final OllamaService ollamaService;

    // Constructor Injection
    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateResponse(@RequestBody PromptRequest request) {
        try {
            // Logic moved to service
            String response = ollamaService.askModel(request.getModel(), request.getPrompt());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error communicating with Ollama: " + e.getMessage());
        }
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamAI(@RequestParam String prompt, @RequestParam String model) {
        // Corrected: Now calling the service instead of a non-existent webClient
        return ollamaService.streamModel(model, prompt);
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Spring Boot bridge to Ollama is active.");
    }
}