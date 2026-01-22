package com.ecommerce.application.price.service.impl;

import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import com.ecommerce.domain.price.exception.PriceNotFoundException;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.price.persistence.PriceInquiryPersistence;
import com.ecommerce.domain.product.model.ProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceInquiryServiceTest {

    @Mock
    private PriceInquiryPersistence priceInquiryPersistence;

    @InjectMocks
    private PriceInquiryServiceImpl priceInquiryService;

    @Test
    void testPriceInquiryInvalidParameterException() {
        final LocalDateTime now = LocalDateTime.now();
        final BrandId brandId = new BrandId(1L);
        final ProductId productId = new ProductId(1L);
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(null, null, null)
        );
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(null, productId, now)
        );
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(brandId, null, now)
        );
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(brandId, productId, null)
        );
    }

    @Test
    void testGetFinalPriceSuccess() {
        final LocalDateTime now = LocalDateTime.now();
        final BrandId brandId = new BrandId(1L);
        final ProductId productId = new ProductId(1L);
        final Price price = new Price();
        price.setFinalPrice(BigDecimal.valueOf(35.50));

        when(priceInquiryPersistence.getFinalPrice(brandId, productId, now)).thenReturn(Optional.of(price));

        Price result = priceInquiryService.getFinalPrice(brandId, productId, now);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(35.50), result.getFinalPrice());
        verify(priceInquiryPersistence).getFinalPrice(brandId, productId, now);
    }

    @Test
    void testGetFinalPriceNotFound() {
        final LocalDateTime now = LocalDateTime.now();
        final BrandId brandId = new BrandId(1L);
        final ProductId productId = new ProductId(1L);

        when(priceInquiryPersistence.getFinalPrice(brandId, productId, now)).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () ->
            priceInquiryService.getFinalPrice(brandId, productId, now)
        );
        verify(priceInquiryPersistence).getFinalPrice(brandId, productId, now);
    }

}
