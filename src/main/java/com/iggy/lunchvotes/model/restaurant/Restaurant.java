package com.iggy.lunchvotes.model.restaurant;


import com.iggy.lunchvotes.HasId;
import com.iggy.lunchvotes.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant extends BaseEntity implements HasId {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishes = new ArrayList<>();

//    @OneToMany(mappedBy = "restaurant")
//    private List<User> users = new ArrayList<>();

}