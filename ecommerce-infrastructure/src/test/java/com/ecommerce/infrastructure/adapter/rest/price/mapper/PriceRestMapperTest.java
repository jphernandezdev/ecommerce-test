package com.ecommerce.infrastructure.adapter.rest.price.mapper;

import com.ecommerce.api.model.PriceResponseDto;
import com.ecommerce.domain.brand.model.Brand;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.pricelist.model.PriceList;
import com.ecommerce.domain.product.model.Product;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceRestMapperTest {

    private final PriceRestMapper mapper = Mappers.getMapper(PriceRestMapper.class);

    @Test
    void shouldMapPriceToPriceResponseDto() {
        // Given
        Price price = getPrice();

        // When
        PriceResponseDto dto = mapper.toResponseDto(price);

        // Then
        assertNotNull(dto);
        assertEquals(price.getBrand().getId(), dto.getBrandId());
        assertEquals(price.getProduct().getId(), dto.getProductId());
        assertEquals(price.getPriceList().getId(), dto.getPriceList());
        assertEquals(price.getStartDate(), dto.getStartDate());
        assertEquals(price.getEndDate(), dto.getEndDate());
        assertEquals(price.getFinalPrice().doubleValue(), dto.getPrice());
    }

    private static @NonNull Price getPrice() {
        Brand brand = new Brand();
        brand.setId(1L);

        Product product = new Product();
        product.setId(35455L);

        PriceList priceList = new PriceList();
        priceList.setId(1L);

        Price price = new Price();
        price.setId(1L);
        price.setBrand(brand);
        price.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        price.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        price.setPriceList(priceList);
        price.setProduct(product);
        price.setPriority(0);
        price.setFinalPrice(new BigDecimal("35.50"));
        return price;
    }

    @Test
    void shouldReturnNullWhenPriceIsNull() {
        assertNull(mapper.toResponseDto(null));
    }

    @Test
    void shouldMapPriceToPriceResponseDtoWithPartialFields() {
        // Given
        Price price = new Price();
        price.setId(1L);
        price.setFinalPrice(new BigDecimal("35.50"));
        // Other fields are null

        // When
        PriceResponseDto dto = mapper.toResponseDto(price);

        // Then
        assertNotNull(dto);
        assertEquals(35.50, dto.getPrice());
        assertNull(dto.getBrandId());
        assertNull(dto.getProductId());
        assertNull(dto.getPriceList());
        assertNull(dto.getStartDate());
        assertNull(dto.getEndDate());
    }
}
