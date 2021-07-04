package com.company.StudFarm.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Horse {
    @Id
    @SequenceGenerator(name = "horse_gen", sequenceName = "horseseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "horse_gen")
    private  int id;
    private String name;
    private String breed;
    private String img;

    @ManyToOne
    private Stable stable;

    public Horse(String name, String breed, String img, Stable stable) {
        this.name = name;
        this.breed = breed;
        this.img = img;
        this.stable = stable;
    }
}
