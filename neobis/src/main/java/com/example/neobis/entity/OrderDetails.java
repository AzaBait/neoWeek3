package com.example.neobis.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Order orderId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private Car carId;
    private double totalPrice;
    private LocalDateTime orderDate;

    @PrePersist
    public void prePersist() {
        orderDate = LocalDateTime.now();
    }
}
