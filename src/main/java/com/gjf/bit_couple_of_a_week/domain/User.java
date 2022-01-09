package com.gjf.bit_couple_of_a_week.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "school_id")
    private String schoolId;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "name")
    private String name;
    @Column(name = "male")
    private Boolean male;
    @Column(name = "description")
    private String description;
}
