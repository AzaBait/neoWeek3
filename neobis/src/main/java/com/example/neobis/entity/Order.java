package com.example.neobis.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;
    private LocalDateTime orderDate;

    @PrePersist
    public void prePersist() {
        orderDate = LocalDateTime.now();
    }
}
