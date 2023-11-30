package com.example.chathealth.gpt.service;


import com.example.chathealth.gpt.domain.*;
import com.example.chathealth.team.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Service
public class GptSportRoutineService {
    private final SportRoutineListRepository sportRoutineListRepository;
    private final SportRoutineRepository sportRoutineRepository;
    private final SportCategoryRepository sportCategoryRepository;

    @Autowired
    public GptSportRoutineService(SportRoutineListRepository sportRoutineListRepository, SportRoutineRepository sportRoutineRepository, SportCategoryRepository sportCategoryRepository) {
        this.sportRoutineListRepository = sportRoutineListRepository;
        this.sportRoutineRepository = sportRoutineRepository;
        this.sportCategoryRepository = sportCategoryRepository;
    }
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    public String getGptResponseAndSave(String apiKey, String fixedInput, User user) throws IOException {
        String response = getGptRoutine(apiKey, fixedInput);
        saveSportRoutineForUser(user, response);
        return response;
    }// get sport routine and save it to DB

    public String getGptRoutine(String apiKey, String fixedInput) throws IOException {
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

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody.string());
            JsonNode choices = jsonNode.get("choices");
            JsonNode text = choices.get(0).get("text");
            return text.asText();
        }
    }
    public void saveSportRoutineForUser(User user, String response) {
        // JSON 응답 파싱 로직
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(response);
            for (JsonNode node : rootNode) {
                String routineName = node.get("name").asText();
                int sportCategoryId = node.get("id").asInt();
                Integer sportCount = node.get("reps").asInt();
                Integer setCount = node.get("sets").asInt();
                Integer volume = node.get("volume").asInt();

                SportCategory sportCategory = sportCategoryRepository.findById(sportCategoryId)
                        .orElseThrow(() -> new EntityNotFoundException("SportCategory not found"));

                SportRoutineList sportRoutineList = new SportRoutineList();
                // sportRoutineList 필드 설정
                sportRoutineList.setSportCategory(sportCategory);
                sportRoutineList.setSportRoutine(sportRoutineRepository.findBySportRoutineName(routineName).stream().reduce((first, second) -> second).orElseThrow(() -> new EntityNotFoundException("SportRoutine not found")));
                sportRoutineList.setSportCount(sportCount);
                sportRoutineList.setSetCount(setCount);
                sportRoutineList.setVolume(volume);
                sportRoutineListRepository.save(sportRoutineList);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    }





