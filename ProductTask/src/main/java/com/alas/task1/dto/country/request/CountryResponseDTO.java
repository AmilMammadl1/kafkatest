package com.alas.task1.dto.country.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CountryResponseDTO {
    Integer id;
    String name;
    Long population;
}
