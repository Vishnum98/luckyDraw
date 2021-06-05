package com.grofers.luckydraw.model;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList="raffle")})
public class Raffle {
    @Id

    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    String raffle;

    @ManyToOne
    Contest contest;

    public Raffle(String raffle, Contest contest) {
        this.raffle = raffle;
        this.contest = contest;
    }

    public Raffle() {
    }

    public Integer getId() {
        return id;
    }

    public String getRaffle() {
        return raffle;
    }

    public Contest getContest() {
        return contest;
    }
}
