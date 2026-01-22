package com.ecommerce.infrastructure.adapter.jpa.price.repository;

import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.price.persistence.PriceInquiryPersistence;
import com.ecommerce.domain.product.model.ProductId;
import com.ecommerce.infrastructure.adapter.jpa.price.entity.PriceEntity;
import com.ecommerce.infrastructure.adapter.jpa.price.mapper.PriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceInquiryJpaRepositoryAdapter implements PriceInquiryPersistence {

    private final PriceInquiryJpaRepository priceInquiryJpaRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Optional<Price> getFinalPrice(final BrandId brandId, final ProductId productId, final LocalDateTime applicationDate) {
        Optional<PriceEntity> price = priceInquiryJpaRepository.getFinalPrice(brandId.value(), productId.value(), applicationDate);
        return price.map(priceEntityMapper::toDomain);
    }
}
