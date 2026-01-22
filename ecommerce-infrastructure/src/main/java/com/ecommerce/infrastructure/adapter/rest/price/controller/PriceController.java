package com.ecommerce.infrastructure.adapter.rest.price.controller;

import com.ecommerce.api.PricesApi;
import com.ecommerce.api.model.PriceResponseDto;
import com.ecommerce.domain.brand.model.BrandId;
import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.price.service.PriceInquiryService;
import com.ecommerce.domain.product.model.ProductId;
import com.ecommerce.infrastructure.adapter.rest.price.mapper.PriceRestMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@Slf4j
public class PriceController implements PricesApi {

    private final PriceInquiryService priceInquiryService;
    private final PriceRestMapper priceRestMapper;

    @Override
    public ResponseEntity<PriceResponseDto> getFinalPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        log.info("Received request for final price: brandId={}, productId={}, applicationDate={}", brandId, productId, applicationDate);
        Price price = priceInquiryService.getFinalPrice(new BrandId(brandId), new ProductId(productId), applicationDate);
        return ResponseEntity.ok().body(priceRestMapper.toResponseDto(price));
    }

}