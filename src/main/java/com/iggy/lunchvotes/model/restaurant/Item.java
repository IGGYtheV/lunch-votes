package com.iggy.lunchvotes.model.restaurant;

import com.iggy.lunchvotes.model.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "title is mandatory")
    private String title;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "description is mandatory")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Restaurant restaurant;

}