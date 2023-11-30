package com.example.chathealth.gpt.service;


import com.example.chathealth.gpt.domain.Diet;
import com.example.chathealth.gpt.domain.DietRepository;
import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GptDietService {
    //getGptResponseAndSave
    //getGptResponse
    //saveDietForUser


    private final DietRepository dietRepository;
    private final UserRepository userRepository;
    @Autowired
    public GptDietService(DietRepository dietRepository,UserRepository userRepository) {
        this.dietRepository = dietRepository;
        this.userRepository = userRepository;
    }

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();


    @Transactional
    @Scheduled(cron = "0 0 6 * * ?", zone = "Asia/Seoul") // execute at 6:00 AM every day
    public String getGptResponseAndSave(String apiKey, String fixedInput, User user) throws IOException {
        String response = getGptResponse(apiKey, fixedInput);
        saveDietForUser(user, response);
        return response;
    }// get diet and save it to DB

    @Transactional
    public String getGptResponse(String apiKey, String fixedInput) throws IOException {
        String url = "https://api.openai.com/v1/chat/completions";
        MediaType mediaType = MediaType.parse("application/json");
        String requestBody = String.format("{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\",\"content\": \"%s\"}],\"temperature\": 0.5}", fixedInput);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(RequestBody.create(mediaType, requestBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ResponseBody responseBody = response.body();
            if (responseBody == null) throw new IOException("Response body is null");

            JsonNode jsonNode = new ObjectMapper().readTree(responseBody.string());
            return jsonNode.get("choices").get(0).get("message").get("content").asText();
        }

    }
    @Transactional
    public void saveDietForUser(User user, String dietContent) { // 유저 정보와 식단
        Diet diet = new Diet();
        diet.setUser(user);
        diet.setDiet(dietContent);
        dietRepository.save(diet);
    }

    // get All diet List from db
    @Transactional(readOnly = true)
    public List<Diet> getDietsByUser(Long userId) {
         User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        return  dietRepository.findAllByUser(user).stream()
                .collect(Collectors.toList());

    }
// delete Api

    public void deleteDiet(Long userId, Long dietId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Diet diet = dietRepository.findByUserAndId(user, dietId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid diet ID"));

        if (diet.getUser().getId() != user.getId()) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        dietRepository.delete(diet);
    }






}