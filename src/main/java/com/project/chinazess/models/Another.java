package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Another {

    public Another() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Long id;

    private Long another;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date = LocalDate.now();

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm")
    private LocalTime time = LocalTime.now();

    @ManyToOne
    @JoinColumn(name="count_id")
    private Count count;

    public Another(Long another, String description) {
        this.another = another;
        this.description = description;
    }
}
