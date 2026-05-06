package com.shopping.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {
    private Long id;
    private Long clientId;
    private BigDecimal totalAmount;
    private String status;
}