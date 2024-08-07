package com.alas.task1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "country")
@NoArgsConstructor
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
    @SequenceGenerator(name = "country_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Integer id;

    private String name;
    private Long population;
}
