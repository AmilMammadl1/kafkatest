package com.alas.task1.dto.country.request;

import lombok.Data;

@Data
public class CountryUpdateRequestDTO {
    Integer id;
    String name;
    Long population;
}
