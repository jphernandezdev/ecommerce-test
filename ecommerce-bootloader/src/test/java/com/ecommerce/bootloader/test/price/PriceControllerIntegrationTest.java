package com.ecommerce.bootloader.test.price;

import com.ecommerce.domain.common.model.ErrorCatalog;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerIntegrationTest {

    private static final String BASE_URL = "http://localhost:";
    private static final String API_PATH = "/api/v1/prices";

    @LocalServerPort
    private Integer port;

    /*
     * Request at 10:00 on the 14th for product 35455 for brand 1 (ZARA)
     */
    @Test
    void requestProduct35455_Brand1_Date20200614_1000_thenPrice35() {
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH + "?brandId=1&productId=35455&applicationDate=2020-06-14T10:00:00")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(1))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(35.50F))
                .extract();
    }

    /*
     * Request at 16:00 on the 14th for product 35455 for brand 1 (ZARA)
     */
    @Test
    void requestProduct35455_Brand1_Date20200614_1600_thenPrice25() {
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH + "?brandId=1&productId=35455&applicationDate=2020-06-14T16:00:00")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(2))
                .body("startDate", Matchers.equalTo("2020-06-14T15:00:00"))
                .body("endDate", Matchers.equalTo("2020-06-14T18:30:00"))
                .body("price", Matchers.is(25.45F))
                .extract();
    }

    /*
     * Request at 21:00 on the 14th for product 35455 for brand 1 (ZARA)
     */
    @Test
    void requestProduct35455_Brand1_Date20200614_2100_thenPrice35() {
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH + "?brandId=1&productId=35455&applicationDate=2020-06-14T21:00:00")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(1))
                .body("startDate", Matchers.equalTo("2020-06-14T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(35.50F))
                .extract();
    }

    /*
     * Request at 10:00 on the 15th for product 35455 for brand 1 (ZARA)
     */
    @Test
    void requestProduct35455_Brand1_Date20200615_1000_thenPrice30() {
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH + "?brandId=1&productId=35455&applicationDate=2020-06-15T10:00:00")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(3))
                .body("startDate", Matchers.equalTo("2020-06-15T00:00:00"))
                .body("endDate", Matchers.equalTo("2020-06-15T11:00:00"))
                .body("price", Matchers.is(30.50F))
                .extract();
    }

    /*
     * Request at 21:00 on the 16th for product 35455 for brand 1 (ZARA)
     */
    @Test
    void requestProduct35455_Brand1_Date20200616_2100_thenPrice38() {
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH + "?brandId=1&productId=35455&applicationDate=2020-06-16T21:00:00")
                .then().log().body()
                .contentType("application/json")
                .statusCode(200).and()
                .body("productId", Matchers.equalTo(35455))
                .body("brandId", Matchers.equalTo(1))
                .body("priceList", Matchers.equalTo(4))
                .body("startDate", Matchers.equalTo("2020-06-15T16:00:00"))
                .body("endDate", Matchers.equalTo("2020-12-31T23:59:59"))
                .body("price", Matchers.is(38.95F))
                .extract();
    }
    
    @Test
    void requestGeneratePriceNotFoundError(){
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH + "?brandId=999&productId=999&applicationDate=2020-06-16T21:00:00")
                .then().log().body()
                .contentType("application/json")
                .statusCode(404).and()
                .body("code", Matchers.equalTo(ErrorCatalog.PRICE_NOT_FOUND.getCode()))
                .extract();
    }

    @Test
    void requestGenerateGenericError(){
        RestAssured
                .when()
                .get(BASE_URL + port + API_PATH)
                .then().log().body()
                .contentType("application/json")
                .statusCode(500).and()
                .body("code", Matchers.equalTo(ErrorCatalog.GENERIC_ERROR.getCode()))
                .extract();
    }

}