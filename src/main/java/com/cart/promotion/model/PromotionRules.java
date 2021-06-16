package com.cart.promotion.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ConfigurationProperties(prefix = "rules")
@Data
@Configuration
@EnableConfigurationProperties
public class PromotionRules {
    private List<PromotionRule> promotionalRules;

}
