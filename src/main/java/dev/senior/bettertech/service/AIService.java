package dev.senior.bettertech.service;

import dev.senior.bettertech.BetterTechApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {
    private final RestTemplate restTemplate = new RestTemplate();

    public String generateFeedback(String submissionContent) {
        String apiKey = BetterTechApplication.dotenv.get("OPENAI_API_KEY");

        if (apiKey == null) {
            throw new IllegalStateException("API key not found in .env");
        }

        String prompt = "Provide detailed feedback for the following submission:\n\n" + submissionContent;

        // OpenAI API URL
        String apiUrl = "https://api.openai.com/v1/completions";

        // Construct the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Create the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003"); // Or the model you prefer
        requestBody.put("prompt", prompt);
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 500);

        // Wrap the body and headers into an HttpEntity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // Make the API request
            ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, Map.class);

            // Parse the response
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                Map<String, Object> firstChoice = ((List<Map<String, Object>>) responseBody.get("choices")).get(0);
                return (String) firstChoice.get("text");
            }
            return "No feedback generated.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while generating feedback.";
        }
    }
}
