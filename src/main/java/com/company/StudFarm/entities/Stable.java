package com.company.StudFarm.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Stable {
    @Id
    @SequenceGenerator(name = "stable_gen", sequenceName = "stableseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stable_gen")
    private int id;
    private String name;
    private int boxCount;
    private Date lastRenovationDate;
    private String img;
    private String description;


    public Stable(String name, int boxCount, Date lastRenovationDate, String img, String description) {
        this.name = name;
        this.boxCount = boxCount;
        this.lastRenovationDate = lastRenovationDate;
        this.img = img;
        this.description = description;
    }
}
