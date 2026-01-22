package com.ecommerce.bootloader.test.price;

import com.ecommerce.application.price.service.impl.PriceInquiryServiceImpl;
import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import com.ecommerce.domain.product.model.ProductId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PriceInquiryServiceTest {

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

}