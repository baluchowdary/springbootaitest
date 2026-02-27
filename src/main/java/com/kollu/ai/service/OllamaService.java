package com.kollu.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:11434").build();
    }

    // Standard Request
    public String askModel(String model, String prompt) {
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(Map.of("model", model, "prompt", prompt, "stream", false))
                .retrieve()
                .bodyToMono(Map.class)
                .map(res -> res.get("response").toString())
                .block();
    }

    // Streaming Request
    public Flux<String> streamModel(String model, String prompt) {
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(Map.of("model", model, "prompt", prompt, "stream", true))
                .retrieve()
                .bodyToFlux(Map.class)
                .map(res -> res.get("response").toString());
    }
}