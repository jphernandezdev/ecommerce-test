CREATE TABLE prices
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id   BIGINT         NOT NULL,
    start_date TIMESTAMP      NOT NULL,
    end_date   TIMESTAMP      NOT NULL,
    price_list BIGINT         NOT NULL,
    product_id BIGINT         NOT NULL,
    priority   INT            NOT NULL,
    price      NUMERIC(20, 2) NOT NULL,
    currency   VARCHAR(3)     NOT NULL
);

CREATE INDEX idx_prices_brand_product ON prices (brand_id, product_id);
CREATE INDEX idx_prices_dates ON prices (start_date, end_date);
CREATE INDEX idx_prices_search_optimized ON prices (brand_id, product_id, start_date, end_date, priority DESC);
