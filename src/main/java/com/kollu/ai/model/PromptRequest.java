package com.kollu.ai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // Generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor   // Generates a blank constructor (required for JSON mapping)
@AllArgsConstructor  // Generates a constructor with all fields
public class PromptRequest {
    private String model;
    private String prompt;
}