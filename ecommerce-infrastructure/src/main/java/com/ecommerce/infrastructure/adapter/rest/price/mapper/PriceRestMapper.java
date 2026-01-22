package com.ecommerce.infrastructure.adapter.rest.price.mapper;

import com.ecommerce.api.model.PriceResponseDto;
import com.ecommerce.domain.price.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceRestMapper {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "priceList.id", target = "priceList")
    @Mapping(source = "finalPrice", target = "price")
    PriceResponseDto toResponseDto(Price price);

}
