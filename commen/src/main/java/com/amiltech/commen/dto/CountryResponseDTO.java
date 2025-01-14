package com.amiltech.commen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private Long population;
}
