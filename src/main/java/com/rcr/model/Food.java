package com.rcr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long price;
    @ManyToOne
    private Category foodCategory;
    @Column(length = 1000)
    private List<String> images;
    private boolean isAvailable;
    @ManyToOne
    private Restaurant restaurant;
    private boolean isVegetarian;
    private boolean isSeasonal;
    @ManyToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();
    private Date creationDate;


}
