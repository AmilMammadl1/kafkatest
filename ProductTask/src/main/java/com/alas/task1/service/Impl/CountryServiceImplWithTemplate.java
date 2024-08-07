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
import org.springframework.data.domain.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
//@Primary
public class CountryServiceImplWithTemplate implements CountryService {

    RedisTemplate<String, List<Country>> redisTemplate2;
    RedisTemplate<String, Country> redisTemplate;
    CountryRepository countryRepository;

    @Override
    public Country createCountry(CountryCreateRequestDTO dto) {
        Country country = new Country();
        country.setName(dto.getName());
        country.setPopulation(dto.getPopulation());
        Country savedCountry = countryRepository.save(country);

        String cacheKey = "countries::" + savedCountry.getId();
        redisTemplate.opsForValue().set(cacheKey, savedCountry);
        return savedCountry;

    }

    @Override
    public Country readCountryById(CountryReadRequestDTO dto) {
        String cacheKey = "countries::" + dto.getId();
        // Check if the data is in the cache
        Country cachedCountry = (Country) redisTemplate.opsForValue().get(cacheKey);
        if (cachedCountry != null) {
            // Data found in cache
            return cachedCountry;
        } else {
            // Data not found in cache, query the database
            Country country = countryRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            // Optionally add the fetched data to the cache
            if (country != null) {
                redisTemplate.opsForValue().set(cacheKey, country);
            }
            return country;
        }
    }


    @Override
    public Country updateCountry(CountryUpdateRequestDTO dto) {
        // Construct cache key
        String cacheKey = "countries::" + dto.getId();

        // Retrieve the cached country
        Country cachedCountry = (Country) redisTemplate.opsForValue().get(cacheKey);

        Country country;

        if (cachedCountry != null) {
            // If found in cache, use cached country
            country = cachedCountry;
        } else {
            // If not found in cache, fetch from database
            country = countryRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
        }

        // Update the country details
        country.setName(dto.getName());
        country.setPopulation(dto.getPopulation());


        // Save the updated country to the database
        country = countryRepository.save(country);

        // Update the cache with the updated country
        redisTemplate.opsForValue().set(cacheKey, country);

        return country;
    }


    @Override
    public Page<Country> readAllCountries(int page, int size) {
        // Construct cache key based on pagination parameters
        String cacheKey = "countries::page:" + page + "::size:" + size;

        // Retrieve the cached page of countries
        List<Country> countries = redisTemplate2.opsForValue().get(cacheKey);


        if (countries != null) {
            Page<Country> countries2 = new PageImpl<>(countries);
            // If found in cache, return the cached page
            return countries2;
        } else {
            // If not found in cache, fetch from database with pagination and sorting

            Pageable pageable = PageRequest.of(page, size, Sort.by("population").descending());
            List<Country> countries3 = new ArrayList<>();

            Page<Country> all = countryRepository.findAll(pageable);

            all.forEach(countries3::add);


            // Update the cache with the fetched page of countries
            redisTemplate2.opsForValue().set(cacheKey, countries3);

            return all;
        }
    }

    @Override
    public void deleteCountry(CountryDeleteRequestDTO dto) {
        // Construct cache key
        String cacheKey = "countries::" + dto.getId();

        // Check if the country exists in the database
        if (countryRepository.existsById(dto.getId())) {
            // Delete the country from the database
            countryRepository.deleteById(dto.getId());

            // Remove the corresponding entry from the cache
            redisTemplate.delete(cacheKey);

            // Optionally, remove the cache for the list of all countries if needed
            // This depends on whether you want to keep the entire list cache up-to-date
            redisTemplate.delete("countries::all");
        } else {
            throw new RuntimeException("Country not found");
        }
    }

}
