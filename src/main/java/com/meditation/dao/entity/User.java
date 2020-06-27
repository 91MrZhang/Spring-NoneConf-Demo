package com.meditation.dao.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "T_USER")
public class User {
    @Id
    private String pk1;
    private String userId;
    private String password;
}
