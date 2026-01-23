package com.ecommerce.infrastructure.adapter.jpa.price.repository;

import com.ecommerce.infrastructure.adapter.jpa.price.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceInquiryJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
            SELECT p FROM PriceEntity p
            WHERE
                p.brandId = :brandId AND
                p.productId = :productId AND
                :date BETWEEN p.startDate AND p.endDate
            ORDER BY p.priority DESC
            LIMIT 1
            """)
    Optional<PriceEntity> getFinalPrice(Long brandId, Long productId, LocalDateTime date);

}