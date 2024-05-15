package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class Bonus {

    public Bonus() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Long id;

    private Long bonus;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="count_id")
    private Count count;

    public Bonus(Long bonus, String description) {
        this.bonus = bonus;
        this.description = description;
    }
}
