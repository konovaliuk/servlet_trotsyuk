package com.periodicalpublishing.database.entities;

import com.periodicalpublishing.database.entities.enums.PeriodicityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Periodical {
    private Long id;
    private String name;
    private Double price;
    private PeriodicityType periodicityType;
    private int periodicityValue;
}
