package com.alas.task1.service;

import com.alas.task1.dto.country.request.CountryCreateRequestDTO;
import com.alas.task1.dto.country.request.CountryDeleteRequestDTO;
import com.alas.task1.dto.country.request.CountryReadRequestDTO;
import com.alas.task1.dto.country.request.CountryUpdateRequestDTO;
import com.alas.task1.model.Country;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {

    Country createCountry(CountryCreateRequestDTO dto);
    Country readCountryById(CountryReadRequestDTO dto);
    Country updateCountry(CountryUpdateRequestDTO dto);
    Page<Country> readAllCountries(int page,int size);
    void deleteCountry(CountryDeleteRequestDTO dto);
}
