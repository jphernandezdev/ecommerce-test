package com.ecommerce.bootloader.test.common.advice;

import com.ecommerce.domain.common.model.ErrorCatalog;
import com.ecommerce.domain.price.service.PriceInquiryService;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class GenericControllerAdviceTest {

    @LocalServerPort
    private Integer port;

    @MockitoBean
    private PriceInquiryService priceInquiryService;

    @Test
    void whenUnexpectedException_thenHandleGenericError() {
        when(priceInquiryService.getFinalPrice(any(), any(), any()))
                .thenThrow(new RuntimeException("Unexpected error"));

        RestAssured
                .given()
                .port(port)
                .when()
                .get("/api/v1/prices?brandId=1&productId=1&applicationDate=2020-06-14T10:00:00")
                .then()
                .statusCode(500)
                .body("code", Matchers.equalTo(ErrorCatalog.GENERIC_ERROR.getCode()))
                .body("message", Matchers.equalTo(ErrorCatalog.GENERIC_ERROR.getMessage()))
                .body("details", Matchers.hasItem("Unexpected error"));
    }
}
