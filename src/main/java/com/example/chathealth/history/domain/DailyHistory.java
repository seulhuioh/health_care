package com.example.chathealth.history.domain;
import com.example.chathealth.history.domain.CountHistory;
import com.example.chathealth.team.domain.User;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "dailyhistory")
public class DailyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @OneToMany(mappedBy = "dailyHistory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CountHistory> countHistory = new ArrayList<>();
    @Getter
    @ManyToOne //매핑
    private User user = new User();


    @Column(nullable = true, name  ="total_time" )
    private Time totalTime = null;


    @Column(nullable = true, name  ="total_weight" )
    private Integer  totalWeight = null;
    @Getter
    @Column(nullable = false, name = "date")
    private Timestamp date;

    public DailyHistory(User user) {
        this.user = user;
    }

    public DailyHistory(List<CountHistory> countHistory, User user) {
        this.countHistory = countHistory;
        this.user = user;
    }

    protected DailyHistory() {  }

    public Long getId() {return id;}

    public Time getTotalTime() {
        return totalTime;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

}
