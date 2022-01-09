package com.gjf.bit_couple_of_a_week.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "couple")
@ToString
public class Couple {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "launcher")
    private Integer launcher;
    @Column(name = "male")
    private Integer maleId;
    @Column(name = "female")
    private Integer femaleId;
    @Column(name = "status")
    private String status;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "duration")
    private int duration;
    @Column(name = "note")
    private String note;
}
