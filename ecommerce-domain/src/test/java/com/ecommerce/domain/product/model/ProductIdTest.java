package com.ecommerce.domain.product.model;

import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    @DisplayName("Should create ProductId when value is not null")
    void shouldCreateProductIdWhenValueIsNotNull() {
        Long value = 35455L;
        ProductId productId = new ProductId(value);
        assertEquals(value, productId.value());
    }

    @Test
    @DisplayName("Should throw PriceInquiryInvalidParameterException when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        PriceInquiryInvalidParameterException exception = assertThrows(
                PriceInquiryInvalidParameterException.class,
                () -> new ProductId(null)
        );
        assertEquals("The productId must be specified", exception.getMessage());
    }

    @Test
    @DisplayName("Should be equal when values are same")
    void shouldBeEqualWhenValuesAreSame() {
        ProductId productId1 = new ProductId(35455L);
        ProductId productId2 = new ProductId(35455L);
        assertEquals(productId1, productId2);
        assertEquals(productId1.hashCode(), productId2.hashCode());
    }
}
