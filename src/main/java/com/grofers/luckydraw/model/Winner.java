package com.grofers.luckydraw.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "winners")
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    int id;
    @OneToOne
    User user;
    @ManyToOne
    Gift gift;

    int rank;

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Winner() {
    }

    public Winner(User user, Integer rank, Gift gift) {
        this.user = user;
        this.rank =rank;
        this.gift = gift;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Integer getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return id == winner.id && Objects.equals(user, winner.user) && Objects.equals(rank, winner.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, rank);
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }



}
