package com.example.chathealth.gpt.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
@RestController
public class GptController {
    @Value("${openai.api.key}")
    private String apiKey;

    @GetMapping("/api/gpt")
    public String callGpt() throws IOException {
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        String url = "https://api.openai.com/v1/chat/completions";
        MediaType mediaType = MediaType.parse("application/json");

        // 고정된 입력값 설정
        String fixedInput = "식단 추천";
        String requestBody = String.format("{\"model\": \"gpt-3.5-turbo\",\"messages\": [{\"role\": \"user\",\"content\": \"%s\"}],\"temperature\": 0.7}", fixedInput);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(RequestBody.create(mediaType, requestBody))
                .build();

        Response response = okhttp.newCall(request).execute();

        if (!response.isSuccessful()) {
            System.out.println("요청 실패: " + response.code());
            return "요청 실패";
        }
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            System.out.println("응답 본문이 없습니다.");
            return "응답 본문이 없습니다.";
        }

        JsonNode jsonNode = new ObjectMapper().readTree(responseBody.string());
        String output = jsonNode.get("choices").get(0).get("message").get("content").asText();

        return output;
    }
}
