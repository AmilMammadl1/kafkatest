package com.alas.task1.service.Impl;

import com.alas.task1.dto.country.request.CountryCreateRequestDTO;
import com.alas.task1.dto.country.request.CountryDeleteRequestDTO;
import com.alas.task1.dto.country.request.CountryReadRequestDTO;
import com.alas.task1.dto.country.request.CountryUpdateRequestDTO;
import com.alas.task1.model.Country;
import com.alas.task1.repository.CountryRepository;
import com.alas.task1.service.CountryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CountryServiceImplWithCahceAnnotations implements CountryService {

    CountryRepository countryRepository;

    @Override
    @CachePut(value = "countries", key = "#result.id")
    public Country createCountry(CountryCreateRequestDTO dto) {
        Country country = new Country();
        country.setName(dto.getName());
        country.setPopulation(dto.getPopulation());
        Country savedCountry = countryRepository.save(country);
        // Log or debug to check if savedCountry is not null and has an id
        System.out.println("Saved Country: " + savedCountry);
        return savedCountry;  // Return the saved country to update the cache entry
    }


    @Override
    @Cacheable(value = "countries", key = "#dto.id")
    public Country readCountryById(CountryReadRequestDTO dto) {
        Country country = countryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Country not found"));
        return country;
    }

    @Override
    @CachePut(value = "countries", key = "#dto.id")
    public Country updateCountry(CountryUpdateRequestDTO dto) {
        Country country = countryRepository.findById(dto.getId())
                       .orElseThrow(() -> new RuntimeException("Country not found"));
        country.setName(dto.getName());
        country.setPopulation(dto.getPopulation());
        countryRepository.save(country);
        return country;
    }

    @Override
    @Cacheable(value = "countries", key = "'all'")
    public Page<Country> readAllCountries(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("population")));
        return countryRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(value = "countries", key = "#dto.id")
    public void deleteCountry(CountryDeleteRequestDTO dto) {
        if (countryRepository.existsById(dto.getId())) {
            countryRepository.deleteById(dto.getId());
        } else {
            throw new RuntimeException("Country not found");
        }
    }
//
//    public void processAllCountries() {
//        // Call the cached method to get the data
//        List<Country> countries = readAllCountries();
//        // Use the data for further processing
//    }

}
