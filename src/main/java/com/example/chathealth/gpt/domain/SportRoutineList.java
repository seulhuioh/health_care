package com.example.chathealth.gpt.domain;


import javax.persistence.*;

@Entity
@Table(name = "sportroutinelist")
public class SportRoutineList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;

    @ManyToOne
    private SportRoutine sportRoutine = new SportRoutine();
    @ManyToOne
    private SportCategory sportCategory = new SportCategory();
    @Column(nullable = true, name ="sport_count" )
    private Integer  sportCount = null;

    @Column(nullable = true, name ="set_count" )
    private Integer  setCount = null;
    @Column(nullable = true, name ="volume" )
    private Integer  volume = null;


   public SportRoutineList() {
    }

    public SportRoutineList(SportRoutine sportRoutine, SportCategory sportCategory, Integer sportCount, Integer setCount, Integer volume) {
        this.sportRoutine = sportRoutine;
        this.sportCategory = sportCategory;
        this.sportCount = sportCount;
        this.setCount = setCount;
        this.volume = volume;

    }
    public SportRoutineList(SportCategory sportCategory, Integer sportCount, Integer setCount, Integer volume) {
        this.sportCategory = sportCategory;
        this.sportCount = sportCount;
        this.setCount = setCount;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public int getSportCount() {
        return sportCount;
    }

    public int getSetCount() {
        return setCount;
    }

    public int getVolume() {
        return volume;
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

    public void setSportRoutine(SportRoutine sportRoutine) {
       this.sportRoutine = sportRoutine;
    }

    public void setSportCategory(SportCategory sportCategory) {
        this.sportCategory = sportCategory;
    }
}
