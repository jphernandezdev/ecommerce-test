package com.ecommerce.infrastructure.config;

import com.ecommerce.application.common.annotation.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.ecommerce.application",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class)
)
public class UseCaseConfig {
}
