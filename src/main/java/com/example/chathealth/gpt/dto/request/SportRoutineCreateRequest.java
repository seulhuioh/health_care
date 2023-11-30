package com.example.chathealth.gpt.dto.request;

import java.util.List;

public class SportRoutineCreateRequest {
    private String name; // 루틴 이름
    private List<Detail> details; // 운동 세부 정보 리스트

    public String getName() {
        return name;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public static class Detail {
        private String id; // 운동 종목 식별 아이디
        private String reps; // 운동 횟수
        private String sets; // 세트 수
        private String volume; // 무게

        public void setId(String id) {
            this.id = id;
        }

        public void setReps(String reps) {
            this.reps = reps;
        }

        public void setSets(String sets) {
            this.sets = sets;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getId() {
            return id;
        }

        public String getReps() {
            return reps;
        }

        public String getSets() {
            return sets;
        }

        public String getVolume() {
            return volume;
        }
    }
}
