package com.grofers.luckydraw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GenericGenerator(name="increment", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increment")
    private Integer id;
    @Column
    private String name;
    @Column
    private String address;

    @OneToMany(targetEntity = Raffle.class)
    List<Raffle> raffleList;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
        this.raffleList = new ArrayList<>();
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void addRaffle(Raffle contest){
        raffleList.add(contest);
    }
}
