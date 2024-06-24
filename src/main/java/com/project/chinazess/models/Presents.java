package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Presents {

    public Presents() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Long id;

    private Long presents;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm")
    private LocalTime time = LocalTime.now();

    @ManyToOne
    @JoinColumn(name="count_id")
    private Count count;

    public Presents(Long presents, String description) {
        this.presents = presents;
        this.description = description;
    }
}
