package com.gjf.bit_couple_of_a_week.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post")
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "couple_id")
    private int coupleId;
    @Column(name = "male_id")
    private int maleId;
    @Column(name = "female_id")
    private int femaleId;
    @Column(name = "male_content")
    private String maleContent;
    @Column(name = "male_url")
    private String maleUrl;
    @Column(name = "female_content")
    private String femaleContent;
    @Column(name = "female_url")
    private String femaleUrl;
}
