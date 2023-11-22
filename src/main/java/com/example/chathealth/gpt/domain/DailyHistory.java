package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "DailyHistory")
public class DailyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;
    @ManyToOne //매핑
    private User user = new User();
    @Column(nullable = true, name ="user_id" )
    private Long  UserId = null;
    @Column(nullable = true, name ="day_calories" )
    private Integer  DayCalories = null;
    @Column(nullable = true, name ="day_protein" )
    private Integer  DayProtein = null;
    @Column(nullable = true, name ="day_glucose" )
    private Integer  DayGlucose = null;
    @Column(nullable = true, name ="day_fat" )
    private Integer  DayFat = null;
    @Column(nullable = true, name  ="day_workout" )
    private Integer  DayWorkout = null;



    protected DailyHistory() {

    }

    public Long getId() {
        return Id;
    }

    public Long getUserId() {
        return UserId;
    }

    public int getDayCalories() {
        return DayCalories;
    }

    public int getDayProtein() {
        return DayProtein;
    }

    public int getDayGlucose() {
        return DayGlucose;
    }

    public int getDayFat() {
        return DayFat;
    }

    public int getDayWorkout() {
        return DayWorkout;
    }
}
