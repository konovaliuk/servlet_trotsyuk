package com.periodicalpublishing.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPeriodical {
    private Long orderId;
    private Long periodicalId;
}
