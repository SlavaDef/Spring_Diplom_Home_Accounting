package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Salary {

    public Salary() {
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long salary;

    private String description;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm")
    private LocalTime time = LocalTime.now();

    @ManyToOne
    @JoinColumn(name="count_id")
    private Count count;

    public Salary(Long salary, String description) {
        this.salary = salary;
        this.description = description;
    }
}
