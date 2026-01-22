package com.ecommerce.domain.price.persistence;

import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.product.model.ProductId;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceInquiryPersistence {

    Optional<Price> getFinalPrice(BrandId brandId, ProductId productId, LocalDateTime applicationDate);

}
