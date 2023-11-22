package com.example.chathealth.gpt.domain;


import javax.persistence.*;

@Entity
@Table(name = "SportRoutineList")
public class SportRoutineList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;

    @ManyToOne
    private SportRoutine sportRoutine = new SportRoutine();
    @ManyToOne
    private SportCategory sportCategory = new SportCategory();
    @Column(nullable = true, name ="sport_count" )
    private Integer  SportCount = null;

    @Column(nullable = true, name ="set_count" )
    private Integer  SetCount = null;


    protected SportRoutineList() {
    }

    public Long getId() {
        return Id;
    }

    public int getSportCount() {
        return SportCount;
    }

    public int getSetCount() {
        return SetCount;
    }
}
