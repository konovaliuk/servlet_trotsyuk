package com.periodicalpublishing.database.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private Long id;
    private Long userId;
    private Long periodicalId;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Periodical periodical;
    private Boolean isActive;

    public Subscription(Long id, Long userId, Long periodicalId, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.userId = userId;
        this.periodicalId = periodicalId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
