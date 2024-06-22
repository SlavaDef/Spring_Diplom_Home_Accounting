package com.project.chinazess.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;


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
    private LocalDate date = LocalDate.now();

    //  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //  private LocalDate date2 = LocalDate.now();
    // @Temporal(TemporalType.TIME)

    // @JsonFormat(pattern = "HH:mm:ss")
    // private Time time;

    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    // private Date date2 = new Date();

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // private LocalDateTime time = LocalDateTime.now();


    // private java.time.LocalTime localTime = java.time.LocalTime.now();

    //  @Basic(optional = false, fetch = FetchType.LAZY)
    // @Temporal(TemporalType.TIMESTAMP)
    // @JsonFormat(pattern = "HH:mm:ss")
    //  @Value("${app.myDateTime}")
    // @Value("#{T(java.time.LocalTime).parse('${app.timeEvent}', T(java.time.format.DateTimeFormatter).ofPattern('HH:mm:ss'))}")
    //@Value("${time}")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm:ss")

    // @JsonFormat(pattern = "HH:mm:ss")
    //  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
   // @JsonDeserialize(using = LocalDateDeserializer.class)
  //  @JsonSerialize(using = LocalDateSerializer.class)
  //  @Temporal(value = TemporalType.TIME)
  //  private LocalTime time = LocalTime.now();

    @ManyToOne
    @JoinColumn(name = "count_id")
    private Count count;

    public Bonus(Long bonus, String description) {
        this.bonus = bonus;
        this.description = description;
    }

}
