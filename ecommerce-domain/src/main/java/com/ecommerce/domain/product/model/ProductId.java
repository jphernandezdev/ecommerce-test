package com.ecommerce.domain.product.model;

import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;

public record ProductId(Long value) {
    public ProductId {
        if (value == null) {
            throw new PriceInquiryInvalidParameterException("The productId must be specified");
        }
    }
}
