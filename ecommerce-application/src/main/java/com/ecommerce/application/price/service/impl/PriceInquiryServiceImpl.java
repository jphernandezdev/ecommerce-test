package com.ecommerce.application.price.service.impl;

import com.ecommerce.application.common.annotation.ApplicationTransactional;
import com.ecommerce.application.common.annotation.UseCase;
import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import com.ecommerce.domain.price.exception.PriceNotFoundException;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.price.persistence.PriceInquiryPersistence;
import com.ecommerce.domain.price.service.PriceInquiryService;
import com.ecommerce.domain.product.model.ProductId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class PriceInquiryServiceImpl implements PriceInquiryService {

    private static final String NOT_FOUND_ERROR_MSG = "The price was not found for brandId=%s, productId=%s, applicationDate=%s";

    private final PriceInquiryPersistence priceInquiryPersistence;

    @Override
    @ApplicationTransactional(readOnly = true)
    public Price getFinalPrice(final BrandId brandId, final ProductId productId, final LocalDateTime applicationDate) {
        log.debug("getFinalPrice(brandId={}, productID={}, applicationDate={})", brandId, productId, applicationDate);

        if (applicationDate == null) {
            throw new PriceInquiryInvalidParameterException("The parameter applicationDate must be specified");
        }

        return priceInquiryPersistence.getFinalPrice(brandId, productId, applicationDate).orElseThrow(() -> {
            var msg = String.format(NOT_FOUND_ERROR_MSG, brandId.value(), productId.value(), applicationDate);
            return new PriceNotFoundException(msg);
        });
    }

}
