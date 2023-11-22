package com.example.chathealth.gpt.domain;

import javax.persistence.*;

@Entity
@Table(name = "SportCategory")
public class SportCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;
    @OneToMany(mappedBy = "sportCategory")
    private SportRoutineList sportRoutineList = new SportRoutineList();
    @OneToMany(mappedBy = "sportCategory")
    private CountHistory countHistory = new CountHistory();

    @Column(nullable = true, name ="sport_name" )
    private String  SportName =null;
    @Column(nullable = true, name ="part" )
    private String  Part = null;



     protected SportCategory() {

     }


    public Long getId() {
        return Id;
    }

    public String getSportName() {
        return SportName;
    }

    public String getPart() {
        return Part;
    }
}
