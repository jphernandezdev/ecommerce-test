package com.ecommerce.infrastructure.adapter.rest.price.controller;

import com.ecommerce.domain.price.model.Price;
import com.ecommerce.domain.price.service.PriceInquiryService;
import com.ecommerce.infrastructure.adapter.rest.price.mapper.PriceRestMapper;
import com.ecommerce.infrastructure.adapter.rest.price.model.response.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping("/api/v1/prices")
@Tag(name = "Prices", description = "Prices service operations")
@Slf4j
public class PriceController {

    private final PriceInquiryService priceInquiryService;
    private final PriceRestMapper priceRestMapper;

    @GetMapping
    @Operation(summary = "Find the final price for the indicated parameters")
    @ApiResponse(responseCode = "200", description = "Price found for current input data", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", implementation = PriceResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input parameters for price inquiry", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "No price found for the current input parameters", content = @Content(mediaType = "application/json"))
    public ResponseEntity<PriceResponse> getFinalPrice(
            @RequestParam("brandId") Long brandId,
            @RequestParam("productId") Long productId,
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime applicationDate) {
        Price price = priceInquiryService.getFinalPrice(brandId, productId, applicationDate);
        return ResponseEntity.ok().body(priceRestMapper.toResponse(price));
    }

}