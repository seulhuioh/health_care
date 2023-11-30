package com.example.chathealth.gpt.domain;

import com.example.chathealth.history.domain.CountHistory;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sportcategory")
public class SportCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id  ;

    @Getter
    @Column(nullable = true, name ="sport_name" )
    private String  sportName =null;
    @Column(nullable = true, name ="part" )
    private String  part = null;

    @OneToMany(mappedBy = "sportCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //연관 관계의 주인이 아님을 명시
    private List<CountHistory> countHistory = new ArrayList<>();
    @OneToMany(mappedBy = "sportCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //연관 관계의 주인이 아님을 명시
    private List<SportRoutineList> sportRoutineList= new ArrayList<>();


     protected SportCategory() {}


    public int getId() {
        return id;
    }

    public String getPart() {
        return part;
    }
}
