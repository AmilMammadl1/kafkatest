package com.amiltech.task2.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryResponseDTO {
    Integer id;
    String name;
    Long population;
}
