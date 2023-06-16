package com.practice.springbootassignment.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "food")
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;
    @Column(name = "cuisine")
    @Getter
    @Setter
    private String cuisine;
    @Column(name = "dish")
    @Getter
    @Setter
    private String dish;

    public Food(String name, String cuisine, String dish) {
        this.name = name;
        this.cuisine = cuisine;
        this.dish = dish;
    }
}
