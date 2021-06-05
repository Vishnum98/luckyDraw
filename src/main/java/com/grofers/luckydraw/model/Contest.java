package com.grofers.luckydraw.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer contest_id;
    @Column
    private LocalDateTime date;

    @OneToMany
    List<Winner> winners;
    @ManyToMany
    List<Gift> gifts;

    public Contest(LocalDateTime date, List<Gift> gifts) {
        this.date = date; this.gifts = gifts;
    }

    public Contest() {
    }

    public Integer getContest_id() {
        return contest_id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Winner> getWinners() {
        return winners;
    }

    public void setWinners(List<Winner> winners) {
        this.winners = winners;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }
}
