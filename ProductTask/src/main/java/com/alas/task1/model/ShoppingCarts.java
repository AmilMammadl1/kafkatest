package com.alas.task1.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "shopping_carts")
@NoArgsConstructor
public class ShoppingCarts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_seq")
    @SequenceGenerator(name = "carts_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "name")
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "menus_categories",
            joinColumns = @JoinColumn(name = "carts_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

    )
    List<Product> products;
}
