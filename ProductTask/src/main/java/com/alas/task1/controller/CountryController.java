package com.alas.task1.controller;

import com.alas.task1.dto.country.request.*;
import com.alas.task1.model.Country;
import com.alas.task1.producer.KafkaProducerService;
import com.alas.task1.repository.CountryRepository;
import com.alas.task1.service.CountryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/country")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CountryController {

    KafkaProducerService kafkaProducer;

    CountryService countryService;
    private final CountryRepository countryRepository;

    public CountryController(KafkaProducerService kafkaProducer, @Qualifier("countryServiceImplWithCahceAnnotations") CountryService countryService,
                             CountryRepository countryRepository) {
        this.kafkaProducer = kafkaProducer;
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public Page<Country> getAllCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return countryService.readAllCountries(page, size);
    }

    @GetMapping("/by-id")
    public Country getCountryById(@RequestBody CountryReadRequestDTO dto) {
        return countryService.readCountryById(dto);
    }

    @PutMapping("/update")
    public Country updateCountryById(@RequestBody CountryUpdateRequestDTO dto) {
        return countryService.updateCountry(dto);
    }

    @DeleteMapping("/delete")
    public void deleteCountryById(@RequestBody CountryDeleteRequestDTO dto) {
        countryService.deleteCountry(dto);
    }

    @PostMapping("/create")
    public Country createCountry(@RequestBody CountryCreateRequestDTO dto) {
        return countryService.createCountry(dto);
    }

    @PostMapping("/write")
    public void writeKafka() {
        List<com.amiltech.commen.dto.CountryResponseDTO> countryList = new ArrayList<>();
        countryRepository.findAll()
                .forEach(country ->
                        countryList.add(com.amiltech.commen.dto.CountryResponseDTO.builder()
                                .id(country.getId())
                                .name(country.getName())
                                .population(country.getPopulation())
                                .build()));

        for (com.amiltech.commen.dto.CountryResponseDTO countryResponseDTO : countryList) {
            kafkaProducer.send("test2t", countryResponseDTO);
//        }
        }

    }
}
