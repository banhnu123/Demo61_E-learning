package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String coursesName;
    private String sessions;

    private int hours;
    private String description;
    @OneToMany(mappedBy = "courses")
    private List<Lessons> lessons;

}
