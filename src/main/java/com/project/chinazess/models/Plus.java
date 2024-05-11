package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Plus {

    @Id
    @GeneratedValue
    private Long id;

    private Long salary;

    private Long bonus;

    private Long presents;

    @ManyToOne // в одній групі може бути багато контактів
    @JoinColumn(name="count_id")
    private Count count;
}
