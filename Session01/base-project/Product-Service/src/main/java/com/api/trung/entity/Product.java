package com.api.trung.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String imageUrl;

    private String description;

    private Integer stock;

    private Boolean isActive;

    @PrePersist
    private void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }
}
