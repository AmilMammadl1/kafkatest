package com.alas.task1.mapper;

import com.alas.task1.dto.carts.ShoppingCartsRequestDto;
import com.alas.task1.dto.carts.ShoppingCartsResponseDto;
import com.alas.task1.model.Product;
import com.alas.task1.model.ShoppingCarts;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingCartsMapper {
    ShoppingCarts mapShoppingCartyRequestToEntity(ShoppingCartsRequestDto requestDto);

    //@Mapping(target = "productIds", source = "products")
    ShoppingCartsResponseDto mapEntityToShoppingCartResponse(ShoppingCarts shoppingCarts);

    default List<Integer> mapProductsToProductIds(List<Product> products) {
        if (products == null) {
            return null;
        }
        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }
}
