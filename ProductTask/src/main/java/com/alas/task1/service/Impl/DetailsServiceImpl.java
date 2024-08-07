package com.alas.task1.service.Impl;

import com.alas.task1.dto.productDetails.ProductDetailsRequestDto;
import com.alas.task1.dto.productDetails.ProductDetailsResponseDto;
import com.alas.task1.exception.CustomException;
import com.alas.task1.mapper.DetailsMapper;
import com.alas.task1.model.Product;
import com.alas.task1.model.ProductDetails;
import com.alas.task1.repository.DetailsRepository;
import com.alas.task1.repository.ProductRepository;
import com.alas.task1.service.DetailsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DetailsServiceImpl implements DetailsService {
    DetailsMapper mapper;
    DetailsRepository detailsRepository;
    ProductRepository productRepository;

    @Override
    public ProductDetailsResponseDto createProductDetails(ProductDetailsRequestDto detailsRequestDto, Integer productId) {
        ProductDetails details = mapper.mapProductDetailsRequestToEntity(detailsRequestDto);
        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("not found"));
        details.setProduct(product);
        return mapper.mapEntityToProductDetailsResponse(detailsRepository.save(details));
    }

    @Override
    public void delete(Integer id) {
        detailsRepository.deleteById(id);
    }

}
