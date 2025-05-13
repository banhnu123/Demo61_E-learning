package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Lessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lessonsName;
    private String description;
    private int hours;
    @ManyToOne
    @JoinColumn(name = "courses_id")
    private Courses courses;
}
