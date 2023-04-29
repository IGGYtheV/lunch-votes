package com.iggy.lunchvotes.model.restaurant;

import com.iggy.lunchvotes.HasId;
import com.iggy.lunchvotes.model.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
public class Dish extends BaseEntity implements HasId {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;

    @Column(name = "title", length =100, nullable = false)
    @NotBlank(message = "title is mandatory")
    private String title;

    @Column(name = "price", nullable = false)
    @NotBlank(message = "price is mandatory")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action= OnDeleteAction.NO_ACTION)
    private Restaurant restaurant;

}