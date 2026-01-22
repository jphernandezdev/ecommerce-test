package com.ecommerce.bootloader.test.price;

import com.ecommerce.application.price.service.impl.PriceInquiryServiceImpl;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
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
        LocalDateTime now = LocalDateTime.now();
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(null, null, null)
        );
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(null, 1L, now)
        );
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(1L, null, now)
        );
        assertThrows(PriceInquiryInvalidParameterException.class, () ->
            priceInquiryService.getFinalPrice(1L, 1L, null)
        );
    }

}