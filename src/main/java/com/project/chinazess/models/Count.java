package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Long id;

    private Long count;

    @OneToMany(mappedBy="count", cascade=CascadeType.ALL)
    private List<Salary> salaries = new ArrayList<>();

    @OneToMany(mappedBy="count", cascade=CascadeType.ALL)
    private List<Bonus> bonuses = new ArrayList<>();

    @OneToMany(mappedBy="count", cascade=CascadeType.ALL)
    private List<Presents> presents = new ArrayList<>();

    @OneToMany(mappedBy="count", cascade=CascadeType.ALL)
    private List<Another> anothers = new ArrayList<>();


    @Override
    public String toString() {
        return "Count{" +
                "salaries=" + salaries +
                ", bonuses=" + bonuses +
                ", presents=" + presents +
                ", anothers=" + anothers +
                '}';
    }
}
