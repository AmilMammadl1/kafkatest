package com.alas.task1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
//@RedisHash("product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productDetails=" + productDetails +
                ", category=" + category +
                ", cartsList=" + cartsList +
                '}';
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product" , fetch = FetchType.LAZY)
    private ProductDetails productDetails;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonIgnore // This ensures cartsList is not included in the JSON response
    private List<ShoppingCarts> cartsList;
}
