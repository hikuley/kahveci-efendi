package com.kahveciefendi.entity;

import com.kahveciefendi.listener.EntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * Created by hikuley on 22.09.2017.
 */

@Entity
@EntityListeners(value = EntityListener.class)
public class Customer extends BaseEntity {

    private String name;
    private String surname;
    private String username;
    private String password;

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
