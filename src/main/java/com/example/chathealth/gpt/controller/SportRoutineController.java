package com.example.chathealth.gpt.controller;

import com.example.chathealth.gpt.domain.*;
import com.example.chathealth.gpt.dto.request.SportRoutineCreateRequest;
import com.example.chathealth.gpt.service.GptSportRoutineService;
import com.example.chathealth.gpt.service.SportRoutineService;
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
@RequestMapping("/api/sportRoutines")
public class SportRoutineController {
    @Value("${openai.api.key}")
    private String apiKey;
    private final SportRoutineService sportRoutineService;
    private final GptSportRoutineService gptSportRoutineService;
    private final UserRepository userRepository;
    private final SportRoutineRepository sportRoutineRepository;
    private final SportRoutineListRepository sportRoutineListRepository;

    @Autowired
    public SportRoutineController(
            GptSportRoutineService gptSportRoutineService,
            SportRoutineService sportRoutineService ,
            UserRepository userRepository,
            SportRoutineRepository sportRoutineRepository,
            SportRoutineListRepository sportRoutineListRepository) {
        this.gptSportRoutineService = gptSportRoutineService;
        this.sportRoutineService = sportRoutineService;
        this.userRepository = userRepository;
        this.sportRoutineRepository = sportRoutineRepository;
        this.sportRoutineListRepository = sportRoutineListRepository;

    }


    // 운동 루틴 생성 -사용자
    @PostMapping("/create/{userId}/user-routine")
 //   @RequestMapping(
    public ResponseEntity<SportRoutine> createSportRoutine(@PathVariable Long userId,
                                                           @RequestBody SportRoutineCreateRequest request) {
        SportRoutine sportRoutine = sportRoutineService.createSportRoutine(userId, request);
        return new ResponseEntity<>(sportRoutine, HttpStatus.CREATED);

    }


    //운동 루틴 생성 - gpt
    @PostMapping("/create/{userId}/gpt")
//    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> callGptForUser(@PathVariable Long userId, @RequestBody String[] additionalInfo) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

            String fixedInput = createFixedInput(user,additionalInfo); // 고정 입력값 생성 - 클라이언트 정보 포함
            String sportRoutineContent = gptSportRoutineService.getGptResponseAndSave(apiKey, fixedInput, user);
            // save the response from GPT to dietContent that typecasted to String

            return ResponseEntity.ok(sportRoutineContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage()); // 500 서버 내부 오류
        }
    }

    private String createFixedInput(User user, String[] additionalInfo) { //유저 정보랑 클라이언트 정보
        // 기본 fixedInput 문자열 설정
        String baseInput = String.format(
                "운동 루틴을 추천해줘. 각각의 아이디와 운동 종목이야. 100 : 스쿼트" +
                        "101 : 레그프레스" +
                        "102 :레그컬" +
                        "103 : 레그익스텐션" +
                        "104 : 런지" +
                        "200: 벤치프레스" +
                        "201 : 덤벨컬" +
                        "202 : 숄더프레스" +
                        "204 : 체스트프레스" +
                        "300 : 데드리프트" +
                        "301 : 덤벨로우" +
                        "302 : 바벨로우" +
                        "303 : 시티드로우" +
                        "304 : 랫풀다운  이중에서 운동루틴을 JSON 형식으로 반환해줘.  사용자의 키는 %dcm이고, 몸무게는 %dkg입니다.",
                user.getHeight(), user.getWeight());

        // 추가 정보를 fixedInput에 통합
        StringBuilder fixedInputBuilder = new StringBuilder(baseInput);
        for (String info : additionalInfo) {
            fixedInputBuilder.append(" ").append(info);
        }

        // 완성된 fixedInput 반환
        return fixedInputBuilder.toString();
    }

    // 운동 루틴 조회 API
    @GetMapping("/find-all/{userId}/routine")
  //  @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SportRoutine>> getUserRoutines(@PathVariable Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));


            List<SportRoutine> routines = sportRoutineRepository
                    .findAllByUser(user).stream().collect(Collectors.toList());
            return ResponseEntity.ok(routines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/find-all/routine-list/{SportRoutineId}")
 //   @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SportRoutineList>> getRoutine( @PathVariable Long SportRoutineId){
        try {

            SportRoutine sportRoutine = sportRoutineRepository.findById(SportRoutineId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid sportRoutine ID"));

            List<SportRoutineList> routineList = sportRoutineListRepository.findAllBySportRoutine(sportRoutine).stream().collect(Collectors.toList());
            return ResponseEntity.ok(routineList);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/delete/{userId}/routine/{routineId}")
  //  @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRoutine(@PathVariable Long userId, @PathVariable Long routineId) {
        try {
            sportRoutineService.deleteSportRoutine(userId, routineId);
            return ResponseEntity.ok("Routine deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }



}
