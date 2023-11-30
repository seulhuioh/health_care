package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sportroutine")
public class SportRoutine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @ManyToOne //매핑
    private User user = new User();
    @OneToMany(mappedBy = "sportRoutine", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //연관 관계의 주인이 아님을 명시
    private List<SportRoutineList> sportRoutineList= new ArrayList<>();

    @Column(nullable = true, name = "sport_routine_name")
    private String sportRoutineName = null;


    //constructor
    public SportRoutine() {
    } // 기본 생성자 -> 새로운 DB삽입시 필요


    public SportRoutine(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(String.format("존재하지 않는 루틴 "));
        }
        this.id = id;

    }

    public SportRoutine(User user,  String sportRoutineName) {
        this.user = user;
        sportRoutineName = sportRoutineName;
    }

    public SportRoutine(String sportRoutineName) {
        if (sportRoutineName == null || sportRoutineName.isBlank()) {
            throw new IllegalArgumentException(String.format("이름을 입력하세요: (%s)", sportRoutineName));
        }
        sportRoutineName = sportRoutineName;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getSportRoutineName() {
        return sportRoutineName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSportRoutineName(String routineName) {
        sportRoutineName = routineName;
    }
}
