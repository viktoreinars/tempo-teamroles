package com.viktortempo.demo.roles;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role {

    private @Id @GeneratedValue Long Id;
    private String name;

    public Role() {}

    public Role(String name){
        this.name = name;
    }
}
