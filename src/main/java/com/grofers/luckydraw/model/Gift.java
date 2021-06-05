package com.grofers.luckydraw.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gift {
    @Id
    @GenericGenerator(name="increment2", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "increment2")
    int id;

    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gift(){

    }
    public Gift(String name) {
        this.name = name;
    }
}
