package com.periodicalpublishing.database.entities;

import com.periodicalpublishing.database.entities.enums.Status;
import lombok.*;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private LocalDate creationDate;
    private Status status;
    private Double totalPrice;

    public Order(Long id, Long userId, LocalDate creationDate, Status status) {
        this.id = id;
        this.userId = userId;
        this.creationDate = creationDate;
        this.status = status;
    }
}
