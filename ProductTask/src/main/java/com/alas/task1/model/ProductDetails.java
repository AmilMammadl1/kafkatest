package com.alas.task1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "product_details")
@NoArgsConstructor
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "details_seq")
    @SequenceGenerator(name = "details_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Integer id;

    private String color;

    private String image_url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore // This ensures cartsList is not included in the JSON response
    private Product product;
}
