package com.shopping.products_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;
}