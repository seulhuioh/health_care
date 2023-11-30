package com.example.chathealth.gpt.controller;

import com.example.chathealth.gpt.domain.Diet;
import com.example.chathealth.gpt.domain.DietRepository;
import com.example.chathealth.gpt.service.GptDietService;
import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/diets")
public class DietController {
    //식단 생성, 조회, 삭제
    private final GptDietService dietService;
    private final UserRepository userRepository;
    private final DietRepository dietRepository;

    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    public DietController(
            GptDietService dietService,
            UserRepository userRepository,
            DietRepository dietRepository)
    {
        this.dietService = dietService;
        this.userRepository = userRepository;
        this.dietRepository = dietRepository;

    }


    @PostMapping("/create/{userId}/gpt/diets") // 원본 코드임 crateFixedInput  외에는 건든거 없음
  //  @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> callGptForUser(@PathVariable Long userId, @RequestBody String[] additionalInfo) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

            String fixedInput = createFixedInput(user,additionalInfo); // 고정 입력값 생성 - 클라이언트 정보 포함
            String dietContent = dietService.getGptResponse(apiKey, fixedInput); // save the response from GPT to dietContent that typecasted to String

            dietService.saveDietForUser(user, dietContent);

            return ResponseEntity.ok(dietContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()); // 500 서버 내부 오류
        }
    }

    private String createFixedInput(User user, String[] additionalInfo) { //유저 정보랑 클라이언트 정보
        // 기본 fixedInput 문자열 설정
        String baseInput = String.format(
                "운동 식단을 추천해줘. 하루 3끼 기준으로 일주일 치 요일 별로, 각 끼니마다 메뉴 한가지만 지정해줘. JSON형식으로 반환해줘. JSON 형식은 { \"월\": { \"아침\":{ \"메뉴\" : \"\", \"분량\" : \"\"}, \"점심\":{ \"메뉴\" : \"\", \"분량\" : \"\"}, \"저녁\": { \"메뉴\" : \"\", \"분량\" : \"\"} }, ... }. 사용자의 키는 %dcm이고, 몸무게는 %dkg입니다.",
                user.getHeight(), user.getWeight());

        // 추가 정보를 fixedInput에 통합
        StringBuilder fixedInputBuilder = new StringBuilder(baseInput);
        for (String info : additionalInfo) {
            fixedInputBuilder.append(" ").append(info);
        }

        // 완성된 fixedInput 반환
        return fixedInputBuilder.toString();
    }

    @GetMapping("/find-all/{userId}/diets")
    public ResponseEntity<List<Diet>> getUserDiets(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        try {
            List<Diet> diets = dietRepository.findAllByUser(user)
                    .stream().collect(Collectors.toList());
            return ResponseEntity.ok(diets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/delete/{userId}/diets/{dietId}")
    public ResponseEntity<String> deleteDiet(@PathVariable Long userId, @PathVariable Long dietId) {
        try {
            dietService.deleteDiet(userId, dietId);
            return ResponseEntity.ok("Diet deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


}
