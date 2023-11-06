package com.example.neobis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {

    private Long id;
    private Long orderId;
    private Long carId;
    private double totalPrice;
    private LocalDateTime orderDate;
}
