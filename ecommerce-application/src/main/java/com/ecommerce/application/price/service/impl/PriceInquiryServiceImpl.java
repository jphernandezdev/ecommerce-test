package com.ecommerce.application.price.service.impl;

import com.ecommerce.application.common.annotation.UseCase;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import com.ecommerce.domain.price.exception.PriceNotFoundException;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.price.persistence.PriceInquiryPersistence;
import com.ecommerce.domain.price.service.PriceInquiryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@UseCase
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PriceInquiryServiceImpl implements PriceInquiryService {

    private final PriceInquiryPersistence priceInquiryPersistence;

    public Price getFinalPrice(final Long brandId, final Long productId, final LocalDateTime applicationDate) {
        log.debug("getFinalPrice(brandId={}, productID={}, applicationDate={})", brandId, productId, applicationDate);

        if (brandId == null || productId == null || applicationDate == null) {
            throw new PriceInquiryInvalidParameterException("The parameters brandId, productId and applicationDate must be specified");
        }

        Optional<Price> price = priceInquiryPersistence.getFinalPrice(brandId, productId, applicationDate);

        if (price.isPresent()) {
            return price.get();
        } else {
            throw new PriceNotFoundException(String.format("The price was not found for brandId=%s, productId=%s, applicationDate=%s", brandId, productId, applicationDate));
        }
    }

}
