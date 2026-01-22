package com.ecommerce.domain.brand.model;

import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandIdTest {

    @Test
    @DisplayName("Should create BrandId when value is not null")
    void shouldCreateBrandIdWhenValueIsNotNull() {
        Long value = 1L;
        BrandId brandId = new BrandId(value);
        assertEquals(value, brandId.value());
    }

    @Test
    @DisplayName("Should throw PriceInquiryInvalidParameterException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PriceInquiryInvalidParameterException exception = assertThrows(
                PriceInquiryInvalidParameterException.class,
                () -> new BrandId(null)
        );
        assertEquals("The brandId must be specified", exception.getMessage());
    }

    @Test
    @DisplayName("Should be equal when values are same")
    void shouldBeEqualWhenValuesAreSame() {
        BrandId brandId1 = new BrandId(1L);
        BrandId brandId2 = new BrandId(1L);
        assertEquals(brandId1, brandId2);
        assertEquals(brandId1.hashCode(), brandId2.hashCode());
    }
}
