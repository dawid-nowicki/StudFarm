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
public class Rental {
    @Id
    @SequenceGenerator(name = "rental_gen", sequenceName = "rentalseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_gen")
    private int id;
    private Date date;

    @OneToOne
    private User customer;

    @OneToOne
    private Horse horse;

    public Rental(User customer, Date date, Horse horse) {
        this.customer = customer;
        this.date = date;
        this.horse = horse;
    }
}
