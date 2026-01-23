package com.ecommerce.infrastructure.adapter.jpa.price.mapper;

import com.ecommerce.domain.price.model.Price;
import com.ecommerce.infrastructure.adapter.jpa.price.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceEntityMapperTest {

    private final PriceEntityMapper mapper = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    void shouldMapPriceEntityToPrice() {
        // Given
        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setBrandId(2L);
        entity.setStartDate(LocalDateTime.of(2020, 6, 14, 0, 0));
        entity.setEndDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59));
        entity.setPriceList(1L);
        entity.setProductId(35455L);
        entity.setPriority(0);
        entity.setPrice(new BigDecimal("35.50"));
        entity.setCurrency("EUR");

        // When
        Price domain = mapper.toDomain(entity);

        // Then
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertNotNull(domain.getBrand());
        assertEquals(entity.getBrandId(), domain.getBrand().getId());
        assertEquals(entity.getStartDate(), domain.getStartDate());
        assertEquals(entity.getEndDate(), domain.getEndDate());
        assertNotNull(domain.getPriceList());
        assertEquals(entity.getPriceList(), domain.getPriceList().getId());
        assertNotNull(domain.getProduct());
        assertEquals(entity.getProductId(), domain.getProduct().getId());
        assertEquals(entity.getPriority(), domain.getPriority());
        assertEquals(entity.getPrice(), domain.getFinalPrice());
        assertNotNull(domain.getCurrency());
        assertEquals(entity.getCurrency(), domain.getCurrency().getIsoCode());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void shouldMapPriceEntityToPriceWithPartialFields() {
        // Given
        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setPrice(new BigDecimal("35.50"));
        // Other fields are null

        // When
        Price domain = mapper.toDomain(entity);

        // Then
        assertNotNull(domain);
        assertEquals(1L, domain.getId());
        assertEquals(new BigDecimal("35.50"), domain.getFinalPrice());
        assertNotNull(domain.getBrand());
        assertNull(domain.getBrand().getId());
        assertNotNull(domain.getProduct());
        assertNull(domain.getProduct().getId());
        assertNotNull(domain.getPriceList());
        assertNull(domain.getPriceList().getId());
        assertNotNull(domain.getCurrency());
        assertNull(domain.getCurrency().getIsoCode());
        assertNull(domain.getStartDate());
        assertNull(domain.getEndDate());
        assertNull(domain.getPriority());
    }
}
