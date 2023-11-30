package com.example.chathealth.history.dto;

import com.example.chathealth.history.domain.DailyHistory;

import java.sql.DatabaseMetaData;
import java.sql.Time;

public class CountHistoryResponse {
    private Integer setCount;
    private Integer sportCount;

    public Integer getVolume() {    return volume;}

    private Integer volume;
    private Time workOutTime;
    private DailyHistory dailyHistory;

    public DailyHistory getDailyHistory() {
        return dailyHistory;
    }

    public void setDailyHistory(DailyHistory dailyHistory) {
        this.dailyHistory = dailyHistory;
    }

    public Integer getSetCount() {
        return setCount;
    }

    public Integer getSportCount() {
        return sportCount;
    }

    public Time getWorkOutTime() {
        return workOutTime;
    }


    public void setSetCount(Integer setCount) {
        this.setCount = setCount;
    }

    public void setSportCount(Integer sportCount) {
        this.sportCount = sportCount;
    }

    public void setWorkOutTime(Time workOutTime) {
        this.workOutTime = workOutTime;
    }

}
