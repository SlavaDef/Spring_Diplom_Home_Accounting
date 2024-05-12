package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

  //  private Long bonus;

  //  private Long presents;

 //   private Long another;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="count_id")
    private Count count;

    public Salary(Long salary, String description) {
        this.salary = salary;
        this.description = description;
    }
}
