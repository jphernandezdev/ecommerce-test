package com.ecommerce.domain.brand.model;

import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;

public record BrandId(Long value) {
    public BrandId {
        if (value == null) {
            throw new PriceInquiryInvalidParameterException("The brandId must be specified");
        }
    }
}
