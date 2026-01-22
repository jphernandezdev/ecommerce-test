package com.ecommerce.domain.price.service;

import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.product.model.ProductId;

import java.time.LocalDateTime;

public interface PriceInquiryService {

    Price getFinalPrice(BrandId brandId, ProductId productId, LocalDateTime applicationDate);

}
