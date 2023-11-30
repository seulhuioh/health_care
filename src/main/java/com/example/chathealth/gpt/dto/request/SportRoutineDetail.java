package com.example.chathealth.gpt.dto.request;

public class SportRoutineDetail {
    private Long sportCategoryId;
    private Integer sportCount;
    private Integer setCount;
    private Integer volume;

    public Long getSportCategoryId() {
        return sportCategoryId;
    }

    public Integer getSportCount() {
        return sportCount;
    }

    public Integer getSetCount() {
        return setCount;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setSportCategoryId(Long sportCategoryId) {
        this.sportCategoryId = sportCategoryId;
    }

    public void setSportCount(Integer sportCount) {
        this.sportCount = sportCount;
    }

    public void setSetCount(Integer setCount) {
        this.setCount = setCount;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
