package com.example.chathealth.gpt.domain;

import javax.persistence.*;

@Entity
@Table(name = "CountHistory")
public class CountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;



    @ManyToOne
    private SportCategory sportCategory;


    @ManyToOne
    @JoinColumn(nullable = false)
    private DailyHistory dailyHistory;
    @Column(nullable = true, name ="set_count" )
    private Integer  SetCount ;


    @Column(nullable = true, name ="sport_count" )
    private Integer  SportCount ;

  /*
  @Column(nullable = false, FinishedTime ="finished_time" )
    private Long  FinishedTime;
*/
    protected CountHistory() {
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

    public void setSportCategory(SportCategory sportCategory) {
        this.sportCategory = sportCategory;
    }

    public void setDailyHistory(DailyHistory dailyHistory) {
        this.dailyHistory = dailyHistory;
    }
}
