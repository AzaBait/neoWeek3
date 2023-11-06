package com.example.neobis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long carId;
    private double totalPrice;
    private LocalDateTime orderDate;
}
